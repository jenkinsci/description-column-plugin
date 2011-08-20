/*
 * The MIT License
 * 
 * Copyright (c) 2011, Axel Haustant
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package info.noirbizarre.jenkins.plugins;

import static org.junit.Assert.*;

import org.junit.Test;

public class DescriptionColumnTest {

	private final static String SIMPLE_DESCRIPTION = "Just a test";
	
	private final static String MULTILINE_DESCRIPTION = "Just a test<br/>Another Line<br>One <b>more</b> line<BR>Last line";
	
	private final static String SEPARATOR = "<br/>";
	
	@Test
	public void formatWithoutTrimming() {
		DescriptionColumn plugin = new DescriptionColumn(false, 2);
		String result = plugin.formatDescription(SIMPLE_DESCRIPTION);
		assertEquals(SIMPLE_DESCRIPTION, result);
		result = plugin.formatDescription(MULTILINE_DESCRIPTION);
		assertEquals(MULTILINE_DESCRIPTION, result);
	}
	
	@Test
	public void formatWithTrimming() {
		DescriptionColumn plugin = new DescriptionColumn(true, 2);
		String result = plugin.formatDescription(SIMPLE_DESCRIPTION);
		assertEquals(SIMPLE_DESCRIPTION, result);
		result = plugin.formatDescription(MULTILINE_DESCRIPTION);
		assertEquals("Just a test<br/>Another Line", result);
		
		plugin = new DescriptionColumn(true, 7);
		result = plugin.formatDescription(MULTILINE_DESCRIPTION);
		assertEquals("Just a test<br/>Another Line<br/>One <b>more</b> line<br/>Last line", result);
	}
}
