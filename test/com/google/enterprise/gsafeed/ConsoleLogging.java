// Copyright 2018 Google Inc. All Rights Reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.enterprise.gsafeed;

import org.junit.rules.ExternalResource;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;

/**
 * Helper to configure the root logger's console logging during a
 * test. Example:
 *
 * <pre>
 * @ClassRule
 * public static ConsoleLogging logging = new ConsoleLogging(Level.OFF);
 * </pre>
 */
class ConsoleLogging extends ExternalResource {
  private Handler consoleHandler;
  private Level prevLevel;
  private Level testLevel;

  ConsoleLogging(Level level) {
    testLevel = level;
    Handler[] handlers =
        LogManager.getLogManager().getLogger("").getHandlers();
    for (Handler handler : handlers) {
      if (handler instanceof ConsoleHandler) {
        consoleHandler = handler;
        prevLevel = consoleHandler.getLevel();
        break;
      }
    }
  }

  @Override
  protected void before() {
    consoleHandler.setLevel(testLevel);
  }

  @Override
  protected void after() {
    consoleHandler.setLevel(prevLevel);
  }
}
