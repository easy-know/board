package com.paul.board.config.p6spy

import com.p6spy.engine.logging.Category.STATEMENT
import com.p6spy.engine.spy.appender.MessageFormattingStrategy
import org.hibernate.engine.jdbc.internal.FormatStyle
import java.text.SimpleDateFormat
import java.util.*

class P6spyPrettySqlFormatter : MessageFormattingStrategy {
    override fun formatMessage(
        connectionId: Int,
        now: String?,
        elapsed: Long,
        category: String?,
        prepared: String?,
        sql: String?,
        url: String?
    ): String {
        val formatSql = formatSql(category, sql)
        val currentDate = Date()
        val simpleFormat = SimpleDateFormat("yy.MM.dd HH:mm:ss")
        return simpleFormat.format(currentDate) + " | " + "OperationTime : " + elapsed + "ms" + formatSql
    }

    private fun formatSql(category: String?, sql: String?): String? {
        var customSql: String? = sql

        if (STATEMENT.name == category) {
            val temp = sql?.trim { it <= ' ' }?.lowercase()
            if (temp != null) {
                customSql = if (temp.startsWith("create") || temp.startsWith("alter") || temp.startsWith("comment")) {
                    FormatStyle.DDL.formatter.format(sql)
                } else {
                    FormatStyle.BASIC.formatter.format(sql)
                }
            }
            customSql = "|\nHeFormatSql(P6Spy sql,Hibernate format):$customSql"
        }

        return customSql
    }
}