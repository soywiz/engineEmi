package engineEmi.Bodies

import com.soywiz.korge.box2d.*
import com.soywiz.korge.view.*
import com.soywiz.korim.color.*
import com.soywiz.korim.vector.*
import com.soywiz.korma.geom.vector.*
import org.jbox2d.collision.shapes.*
import org.jbox2d.common.*
import org.jbox2d.dynamics.*

class Kreis2(x : Double,
             y : Double,
             var bodyType : BodyType = BodyType.STATIC,
             var radius : Float,
             var fillColor : RGBA,
             var strokeColor : RGBA = Colors.BLUE,
             var strokeThickness : Double = 0.0
           ) : Ebody(x = x, y = y) {

    var shape = CircleShape().apply { m_radius = radius }
    var density = 0.0F
    var friction = 0.0F
    var bodyDef = bodyDef {
        type = bodyType
        setPosition(x, y)
    }


    var view = Graphics().apply {
        if (strokeColor != null) {
            fillStroke(Context2d.Color(strokeColor!!), Context2d.Color(strokeColor!!), Context2d.StrokeInfo(thickness = strokeThickness)) {
                circle(x, y, radius)
                //rect(0, 0, 400, 20)
            }
        }
        fill(fillColor) {
            circle(x, y, radius)
        }

    }.scale(1f / 100f)



    lateinit var body : Body


    override fun initBody() {
        this.body = world.createBody(bodyDef).fixture {
            shape = this@Kreis2.shape
            density = this@Kreis2.density
            friction = this@Kreis2.friction
        }.setView(view)
    }

    override fun animate(){
        body.linearVelocity = Vec2(1F, 0.3F)
    }
}


