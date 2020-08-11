package com.epam.quiz.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class IndexController {

	private val index: String = "index"

	@RequestMapping("", "/", "/index")
	fun index(): String {
		return index
	}
}
