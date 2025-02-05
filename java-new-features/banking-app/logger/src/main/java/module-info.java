module org.benek.bootcamp.logger {
    exports org.benek.bootcamp.logger;
    provides org.benek.bootcamp.logger.Logger with org.benek.bootcamp.logger.ConsoleLogger;
}