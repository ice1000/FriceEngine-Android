package org.frice.game.anim

/**
 * Created by ice1000 on 2016/8/15.
 * @author ice1000
 * @since v0.2.1
 */
abstract class FAnim() {
	protected val start: Double = System.currentTimeMillis().toDouble()

	protected val now: Double
		get() = System.currentTimeMillis().toDouble()
}