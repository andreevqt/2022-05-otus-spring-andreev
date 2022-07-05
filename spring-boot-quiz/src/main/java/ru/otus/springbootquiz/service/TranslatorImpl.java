package ru.otus.springbootquiz.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ru.otus.springbootquiz.config.LocaleProvider;

@Service
@AllArgsConstructor
public class TranslatorImpl implements Translator {

  private final MessageSource messageSource;
  private final LocaleProvider localeProvider;

  @Override
  public String translate(String key, Object... args) {
    return messageSource.getMessage(key, args, localeProvider.getLocale());
  }

}
