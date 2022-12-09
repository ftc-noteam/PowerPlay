package asiankoala.ftc2022.commands.sequence.auto

import asiankoala.ftc2022.FileInterface
import com.asiankoala.koawalib.command.commands.InstantCmd

class WriteCmd(key: String, value: String) : InstantCmd({ FileInterface.write(key, value) })