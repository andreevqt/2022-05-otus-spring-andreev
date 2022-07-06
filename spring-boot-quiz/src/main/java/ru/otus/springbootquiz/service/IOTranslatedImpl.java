package ru.otus.springbootquiz.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class IOTranslatedImpl implements IOTranslated {

  private final IOService ioService;
  private final Translator translator;

  @Override
  public void out(String key, Object ...args) {
    ioService.out(translator.translate(key, args));
  }

  @Override
  public int readIntWithPrompt(String key, Object... args) {
    return ioService.readIntWithPrompt(translator.translate(key, args));
  }

  @Override
  public String readStringWithPrompt(String key, Object... args) {
    return ioService.readStringWithPrompt(translator.translate(key, args));
  }

}
