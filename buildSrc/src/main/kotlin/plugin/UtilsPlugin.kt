package plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.type.ArtifactTypeDefinition
import org.gradle.api.attributes.Attribute
import org.gradle.kotlin.dsl.findByType
import org.jetbrains.kotlin.gradle.plugin.KotlinBasePluginWrapper
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

/**
 * @author Marik Lin
 * @date 2021/3/10
 */

class MyPluginExtension {
    var enable = true

    override fun toString(): String {
        return """MyPluginExtension: 
            |   enable: $enable
        """.trimMargin()
    }
}

class UtilsPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        val extension = target.extensions.findByType() ?: MyPluginExtension().also {
            target.extensions.add("myPlugin", it)
        }

        target.plugins.all {
            if (this is KotlinBasePluginWrapper) {
                val configuration = target.configurations.create("kotlinPlugin")

                val kotlinPlugin = configuration.incoming.artifactView {
                    attributes {
                        attribute(
                            Attribute.of("artifactType", String::class.java),
                            ArtifactTypeDefinition.JAR_TYPE
                        )
                    }
                }

                target.tasks.withType(KotlinCompile::class.java) {
                    kotlinOptions {
                        doFirst {
                            if (configuration.isEmpty){
                                return@doFirst
                            }

                            val pluginPath = kotlinPlugin.files.first()

                            println("applying plugin ->  ${pluginPath}")
                            println("extension -> $extension")

                            if (!pluginPath.exists()) {
                                throw IllegalAccessError("cannot access $pluginPath")
                            }

                            freeCompilerArgs += listOf(
                                "-Xopt-in=kotlin.RequiresOptIn",
                                "-Xplugin=${kotlinPlugin.files.first()}",
                                "-P", "plugin:myPlugin:enabled=${extension.enable}"
                            )
                        }
                    }
                }

//                kotlinPlugin.files.forEach {
//                    println("-------------$it")
//                }
            }
        }
    }
}