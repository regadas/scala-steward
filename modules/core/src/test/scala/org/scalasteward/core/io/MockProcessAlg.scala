package org.scalasteward.core.io

import better.files.File
import cats.data.StateT
import cats.effect.IO
import org.scalasteward.core.MockState.MockEnv
import org.scalasteward.core.application.ConfigTest
import org.scalasteward.core.util.Nel

class MockProcessAlg extends ProcessAlg.UsingFirejail[MockEnv](ConfigTest.dummyConfig) {
  override def exec(
      command: Nel[String],
      cwd: File,
      extraEnv: (String, String)*
  ): MockEnv[List[String]] =
    StateT(s => IO.pure((s.exec(command.toList), List.empty[String])))
}
