@file:JvmName("HtmlUtils")
package org.frice.utils

/**
 * HTML tags finder
 * Created by ice1000 on 2016/9/3.
 *
 * @author ice1000
 * @since v0.5
 */

fun findTag(html: String, tag: CharArray): MutableList<String> {
	val c = html.toCharArray()
	val tags = mutableListOf<String>()
	var tagMark = false
	var tagStart = 0
	repeat(c.size - tag.size + 2) { i: Int ->
		// find start index
		if (c[i] == '<') {
			tagMark = (0 until tag.size).none { c[i + it + 1] == tag[it] }
			// cannot use loop{} or forEach.
			// for the reason that I have to break it
			if (tagMark) tagStart = i
		}
		// find end index
		if (tagMark && '>' == c[i]) {
			tagMark = false
			tags.add(html.substring(tagStart..i))
		}
	}
	return tags
}

fun findTag(html: String, tag: String) = findTag(html, tag.toCharArray())
