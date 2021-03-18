package plugin

import org.gradle.api.Project
import org.gradle.kotlin.dsl.findByType

fun Project.myPlugin(action: MyPluginExtension.() -> Unit) {
    extensions.findByType<MyPluginExtension>()?.apply(action)
}