package org.frice.game.utils.graphics.shape

import java.awt.geom.Rectangle2D
import java.util.*

/**
 * Created by ice1000 on 2016/8/14.
 * @author ice1000
 * @since v0.1.1
 */
interface FShape {
	var width: Int
	var height: Int
}


/**
 * Created by ice1000 on 2016/8/14.
 * @author ice1000
 * @since v0.1.1
 */
open class FCircle(r: Double) : FOval(r, r)


/**
 * Created by ice1000 on 2016/8/14.
 * @author ice1000
 * @since v0.1.1
 */
open class FOval(var rh: Double, var rv: Double) : FShape {
	override var width = (rh + rh).toInt()
	override var height = (rv + rv).toInt()
}


/**
 * Created by ice1000 on 2016/8/16.
 * @author ice1000
 * @since v0.3
 */
data class FPoint(var x: Int, var y: Int)


/**
 * Created by ice1000 on 2016/8/14.
 * @author ice1000
 * @since v0.1.1
 */
open class FRectangle(override var width: Int, override var height: Int) : FShape {
	constructor(rect: Rectangle2D) : this(rect.width.toInt(), rect.height.toInt())

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other == null || other !is FRectangle) return false
		if (height != other.height || width != other.width) return false
		return true
	}

	override fun hashCode(): Int {
		var result = width
		result = 31 * result + height
		return result
	}

	//	infix fun rectCollideRect(o: FRectangle) = (x > o.x && )
}

/**
 * 通过两点构造一条直线
 * copied from https://github.com/ice1000/FindLine/blob/master/src/ice1000/models/Line.kt
 * my another repo
 * Created by ice1000 on 2016/8/8.
 *
 * @author ice1000
 */
open class FLine(one: FPoint, two: FPoint) {

	private val a = two.y - one.y
	private val b = one.x - two.x
	private val c = two.x * one.y - one.x * two.y
	val set = HashSet<FPoint>()

	init {
		(Math.min(one.x, two.x)..Math.max(one.x, two.x)).forEach { x -> set.add(FPoint(x, x2y(x))) }
		(Math.min(one.y, two.y)..Math.max(one.y, two.y)).forEach { y -> set.add(FPoint(y2x(y), y)) }
	}

	fun x2y(x: Int) = if (b == 0) c / a else -(a * x + c) / b
	fun y2x(y: Int) = if (a == 0) c / b else -(b * y + c) / a

	override operator fun equals(other: Any?): Boolean {
		if (other == null || other !is FLine) return false
		return a / other.a == b / other.b && b / other.b == c / other.c
	}

	override fun hashCode(): Int {
		var result = a.hashCode()
		result = 31 * result + b.hashCode()
		result = 31 * result + c.hashCode()
		return result
	}
}
