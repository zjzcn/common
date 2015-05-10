package com.zebra.utils;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Locale;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.FileTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public abstract class FreeMarkerUtils {
	public static final Configuration configuration = new Configuration();

	public static final String FILE_BASE_DIR = "c:/freemarker";

	public static final String CLASS_BASE_DIR = "/";

	public static final Locale LOCALE = Locale.US;

	public static final String ENCODING = "UTF-8";

	static {
		FileTemplateLoader fileTemplateLoader = null;
		try {
			fileTemplateLoader = new FileTemplateLoader(new File(FILE_BASE_DIR));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		ClassTemplateLoader classTemplateLoader = new ClassTemplateLoader(FreeMarkerUtils.class, CLASS_BASE_DIR);

		TemplateLoader[] loaders = new TemplateLoader[] { fileTemplateLoader, classTemplateLoader };

		MultiTemplateLoader multiTemplateLoader = new MultiTemplateLoader(loaders);

		configuration.setTemplateLoader(multiTemplateLoader);
		configuration.setLocale(LOCALE);
		configuration.setEncoding(LOCALE, ENCODING);
	}

	private static Template getTemplate(String location) {
		Template template = null;
		try {
			template = configuration.getTemplate(location);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return template;
	}

	public static String processToString(String location, Object rootMap) {
		StringWriter writer = new StringWriter();
		process(location, rootMap, writer);
		return writer.toString();
	}

	public static void process(String location, Object rootMap, Writer writer) {
		process(getTemplate(location), rootMap, writer);
	}

	public static void process(Template template, Object rootMap, Writer writer) {
		try {
			template.process(rootMap, writer);
		} catch (TemplateException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}