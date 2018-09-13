/*
 * Tigase Jabber/XMPP Server
 * Copyright (C) 2004-2018 "Tigase, Inc." <office@tigase.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. Look for COPYING file in the top folder.
 * If not, see http://www.gnu.org/licenses/.
 */

package tigase.util.updater;

import org.junit.Test;
import tigase.util.Version;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UpdatesCheckerTest {

	@Test
//	@Ignore
	public void retrieveVersion() {

		Logger log = Logger.getLogger(UpdatesChecker.class.getName());
		ConsoleHandler ch = new ConsoleHandler();
		ch.setLevel(Level.ALL);
		log.addHandler(ch);
		log.setLevel(Level.ALL);

		UpdatesChecker.ProductInfoIfc product = new TestProduct("my-awesome-product", "My Awesome Product", "1.2.3");
		UpdatesChecker.ProductInfoIfc product2 = new TestProduct("iot-hub", "IoT hub", "7.7.7");
		final List<UpdatesChecker.ProductInfoIfc> products = Arrays.asList(product, product2);

//		String url = "http://atlantiscity.local:1234/rest/update/check/";
		String url = "http://atlantiscity.local:8080/rest/update/check/";

		final Optional<Version> version = UpdatesChecker.retrieveCurrentVersionFromServer(Version.of("8.0.0"), products,
																						  url, 10);
		System.out.println(version);

	}

	private class TestProduct
			implements UpdatesChecker.ProductInfoIfc {

		String id;
		String name;
		String version;

		public TestProduct(String id, String name, String version) {
			this.id = id;
			this.name = name;
			this.version = version;
		}

		@Override
		public String getProductId() {
			return id;
		}

		@Override
		public String getProductName() {
			return name;
		}

		@Override
		public Optional<String> getProductVersion() {
			return Optional.ofNullable(version);
		}
	}
}