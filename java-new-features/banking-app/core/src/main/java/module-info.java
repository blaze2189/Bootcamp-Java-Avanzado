module org.benek.bootcamp.core {
    exports org.benek.bootcamp.core;
    requires transitive org.apache.commons.lang3;
    requires org.benek.bootcamp.logger;
    uses org.benek.bootcamp.logger.Logger;
}