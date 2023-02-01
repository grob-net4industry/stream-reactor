/*
 * Copyright 2020 Lenses.io
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

package io.lenses.streamreactor.connect.aws.s3.sink.conversion

import io.lenses.streamreactor.connect.aws.s3.model.ArraySinkData
import io.lenses.streamreactor.connect.aws.s3.model.MapSinkData
import io.lenses.streamreactor.connect.aws.s3.model.NullSinkData
import io.lenses.streamreactor.connect.aws.s3.model.StringSinkData
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.jdk.CollectionConverters.MapHasAsJava
import scala.jdk.CollectionConverters.SeqHasAsJava

class ToJsonDataConverterTest extends AnyFlatSpec with Matchers {

  "convertMap" should "be able to handle a map of arrays" in {

    ToJsonDataConverter.convertMap(
      Map(
        StringSinkData("abc") -> ArraySinkData(
          Seq(
            StringSinkData("def"),
          ),
        ),
      ),
    ) should be(
      Map(
        "abc" -> List("def").asJava,
      ).asJava,
    )

  }

  "convertArray" should "be able to handle an array of maps" in {

    ToJsonDataConverter.convertArray(
      Seq(
        MapSinkData(
          Map(StringSinkData("abc") -> StringSinkData("def")),
        ),
      ),
    ) should be(List(Map("abc" -> "def").asJava).asJava)

  }

  "convertArray" should "be able to handle null elements in an Array" in {

    ToJsonDataConverter.convertArray(
      Seq(
        MapSinkData(
          Map(StringSinkData("abc") -> StringSinkData("def")),
        ),
        NullSinkData(None),
      ),
    ) should be(List(Map("abc" -> "def").asJava, null).asJava)

  }
}
