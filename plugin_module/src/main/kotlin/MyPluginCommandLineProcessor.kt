import com.google.auto.service.AutoService
import org.jetbrains.kotlin.compiler.plugin.AbstractCliOption
import org.jetbrains.kotlin.compiler.plugin.CliOption
import org.jetbrains.kotlin.compiler.plugin.CommandLineProcessor
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.config.CompilerConfigurationKey

object Options {
    val Enable = CompilerConfigurationKey<Boolean>("enabled")
}


@AutoService(CommandLineProcessor::class)
class MyPluginCommandLineProcessor : CommandLineProcessor {
    override val pluginId: String = "myPlugin"
    override val pluginOptions: Collection<AbstractCliOption> = listOf(
        CliOption(
            optionName = "enabled",
            valueDescription = "<true|false>",
            description = "enable the plugin",
        ),
    )

    override fun processOption(option: AbstractCliOption, value: String, configuration: CompilerConfiguration) {
        println("plugin parameter:${option.optionName},value:$value")

        when (option.optionName) {
            "enabled" -> {
                configuration.put(Options.Enable, value.toBoolean())
            }
        }
    }
}