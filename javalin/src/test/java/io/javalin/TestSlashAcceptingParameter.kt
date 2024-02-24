package io.javalin

import io.javalin.http.HttpStatus.NOT_FOUND
import io.javalin.testing.TestUtil
import io.javalin.testing.httpCode
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TestSlashAcceptingParameter {

    @Test
    fun `slash accepting path param does not match when the param segment is empty`() = TestUtil.test { app, http ->
        app.get("/<name>") { it.result("[" + it.pathParam("name") + "]") }
        assertThat(http.get("/").httpCode()).isEqualTo(NOT_FOUND)
    }

    @Test
    fun `slash accepting path param combined with content does not match when the param segment is empty`() = TestUtil.test { app, http ->
        app.get("/hi/<name>") { it.result("[" + it.pathParam("name") + "]") }
        assertThat(http.get("/hi/").httpCode()).isEqualTo(NOT_FOUND)
        assertThat(http.getBody("/hi/world")).isEqualTo("[world]")
    }

}
