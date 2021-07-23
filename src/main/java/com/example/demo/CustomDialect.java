package com.example.demo;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;


/**
 * 独自定義したダイアレクトを登録する
 */
public class CustomDialect extends AbstractProcessorDialect{
	
	  private static final String NAME = "original dialect";
	  private static final String PREFIX = "dialect";

	  public CustomDialect() {
	          super(NAME, PREFIX, StandardDialect.PROCESSOR_PRECEDENCE);
	    }
	  
      @Override
      public Set<IProcessor> getProcessors(String dialectPrefix) {
        Set<IProcessor> processors = new HashSet<>();

        processors.add(new TextLineProcessor(dialectPrefix, getDialectProcessorPrecedence()));

        return processors;
      }
}
