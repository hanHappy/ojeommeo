package com.ojeommeo.aop

import io.github.oshai.kotlinlogging.KotlinLogging
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.reflect.MethodSignature
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam

private val logger = KotlinLogging.logger {}

@Aspect
@Component
class ControllerLoggingAspect {

    @Around("execution(* com.ojeommeo..*Controller.*(..))")
    fun logAround(joinPoint: ProceedingJoinPoint): Any? {
        val start = System.currentTimeMillis()

        // 호출 메서드
        val methodName = joinPoint.signature.name
        logger.info { "[$methodName] ▶▶▶ API Call" }

        // 파라미터 로깅
        logParameters(joinPoint)

        // 메서드 실행
        val result = joinPoint.proceed() as Any

        // 결과 로깅
        logger.info { "[$methodName] ◀◀◀ Result : $result" }

        val executionTime = System.currentTimeMillis() - start
        logger.info { "[$methodName] Executed in ${executionTime}ms" }

        return result
    }

    fun logParameters(joinPoint: ProceedingJoinPoint) {
        val signature = joinPoint.signature as MethodSignature
        val parameters = signature.method.parameters
        val args = joinPoint.args

        val params: MutableMap<String, Any> = mutableMapOf()
        val pathVariables: MutableMap<String, Any> = mutableMapOf()
        var requestBody: Any? = null

        parameters.forEachIndexed { i, param ->
            val arg = args[i]

            when {
                param.isAnnotationPresent(RequestParam::class.java) -> {
                    val requestParam = param.getAnnotation(RequestParam::class.java)
                    val paramName = requestParam.value.ifEmpty { param.name }
                    paramName?.let { params[it] = arg }
                }

                param.isAnnotationPresent(PathVariable::class.java) -> {
                    val pathVariable = param.getAnnotation(PathVariable::class.java)
                    val paramName = pathVariable.value.ifEmpty { param.name }
                    paramName?.let { pathVariables[it] = arg }
                }

                param.isAnnotationPresent(RequestBody::class.java) -> {
                    requestBody = arg
                }
            }
        }

        val methodName = signature.name

        if (params.isNotEmpty()) logger.info { "[$methodName] @RequestParam : $params" }
        if (pathVariables.isNotEmpty()) logger.info { "[$methodName] @PathVariable : $pathVariables" }
        requestBody?.let { logger.info { "[$methodName] @RequestBody : $it" } }
    }
}
