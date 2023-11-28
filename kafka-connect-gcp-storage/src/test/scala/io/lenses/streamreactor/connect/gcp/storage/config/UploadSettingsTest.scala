/*
 * Copyright 2017-2023 Lenses.io Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.lenses.streamreactor.connect.gcp.storage.config

import io.lenses.streamreactor.common.config.base.traits.BaseSettings
import org.apache.kafka.common.config.types.Password
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import java.lang
import java.util

class UploadSettingsTest extends AnyFunSuite with Matchers with UploadConfigKeys {

  class TestUploadSettings(config: Map[String, String] = Map.empty) extends BaseSettings with UploadSettings {
    override def connectorPrefix: String = "test"

    override def getString(key: String): String = ???

    override def getInt(key: String): Integer = ???

    override def getBoolean(key: String): lang.Boolean =
      config.getOrElse(key, "false").toBoolean

    override def getPassword(key: String): Password = ???

    override def getList(key: String): util.List[String] = ???
  }

  test("isAvoidResumableUpload should default to false") {
    val settings = new TestUploadSettings()
    settings.isAvoidResumableUpload should be(false)
  }

  test("isAvoidResumableUpload should be true when set to true") {
    val settings = new TestUploadSettings(Map(AVOID_RESUMABLE_UPLOAD -> "true"))
    settings.isAvoidResumableUpload should be(true)
  }

  test("isAvoidResumableUpload should be false when set to false") {
    val settings = new TestUploadSettings(Map(AVOID_RESUMABLE_UPLOAD -> "false"))
    settings.isAvoidResumableUpload should be(false)
  }

  test("isAvoidResumableUpload should be false when not explicitly set") {
    val settings = new TestUploadSettings()
    settings.isAvoidResumableUpload should be(false)
  }

  override def connectorPrefix: String = "test"
}
