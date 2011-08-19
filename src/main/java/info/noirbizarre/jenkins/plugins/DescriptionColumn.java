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

import org.kohsuke.stapler.DataBoundConstructor;

import hudson.Extension;
import hudson.views.ListViewColumnDescriptor;
import hudson.views.ListViewColumn;

public class DescriptionColumn extends ListViewColumn {
	
	private boolean trim;
	private int displayLength;
	
	private final static String SEPARATOR = "<br/>";
	private final static String SEPARATORS_REGEX = "(?i)<br\\s*/>|<br>";
	
	@DataBoundConstructor
	public DescriptionColumn(boolean trim, int displayLength) {
		super();
		this.trim = trim;
		this.displayLength = displayLength;
	}
	
	public boolean isTrim() {
		return trim;
	}

	public int getDisplayLength() {
		return displayLength;
	}
	
	public String formatDescription(String desc) {
		if (!trim) {
			return desc;
		}
		String[] parts = desc.split(SEPARATORS_REGEX);
		StringBuffer sb = new StringBuffer();
		for (int i=0; i < displayLength && i < parts.length ; i++) {
			if (i != 0) {
				sb.append(SEPARATOR);
			}
			sb.append(parts[i]);
		}
		return sb.toString();
	}
	
	@Extension
	public static class DescriptorImpl extends ListViewColumnDescriptor {

		@Override
		public boolean shownByDefault() {
			return false;
		}
		
		@Override
		public String getDisplayName() {
			return Messages.DescriptionColumn_DisplayName();
		}
	}
}
