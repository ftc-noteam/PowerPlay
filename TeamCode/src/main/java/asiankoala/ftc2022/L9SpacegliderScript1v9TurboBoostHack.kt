package asiankoala.ftc2022

import com.asiankoala.koawalib.math.Pose
import com.asiankoala.koawalib.math.Vector
import com.asiankoala.koawalib.math.angleWrap
import kotlin.math.PI

class L9SpacegliderScript1v9TurboBoostHack(
    private val obstacles: List<Vector>,
    private val ps: () -> Pose,
    private val ins: () -> Vector,
    private val R: Double,
    private val N: Double,
    private val hp: Double,
) {
    // https://www.desmos.com/calculator/vq9ykp5m3r
    fun spaceglide(): Vector {
        val i = ins.invoke()
        val p = ps.invoke().vec
        val o = obstacles.minByOrNull { (p - it).norm }!!
        val r = p - o
        val v1 = i.unit
        val theta = PI / 2.0
        val d1 = r dot v1
        val rm = r.norm
        val a = N / rm
        val c = r cross v1
        val n2 = r.rotate(if (c >= 0) theta else -theta)
        val v2 = if (d1 >= 0.0 || r.norm > R) v1 else n2 + r * a
        val im = i.norm
        val u = v2.unit
        return u / im
    }

    fun aimbot(): Double {
        val p = ps.invoke()
        val i = ins.invoke()
        val xn = (p.x.toInt() / 24)
        val yn = (p.y.toInt() / 24)
        val c = Vector(xn * 24.0 + 12.0, yn * 24.0 + 12.0)
        val os = listOf(
            Vector(c.x + 12.0, c.y + 12.0),
            Vector(c.x + 12.0, c.y - 12.0),
            Vector(c.x - 12.0, c.y + 12.0),
            Vector(c.x - 12.0, c.y - 12.0)
        )
        val o = os.maxBy { (it - p.vec) dot i }
        val a = (o - p.vec).angle
        val dh = (p.heading - a).angleWrap
        return dh / hp
    }
}
