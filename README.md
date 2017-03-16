# Usage:

to launch the app in devmode: 

from the gwt-websocket-client module: `mvn package gwt:devmode'
and then from the websocket-server module: `mvn spring-boot:run`

To package the app for production:

from the gwt-websocket-client module: `mvn package gwt:devmode -Pproduction'
and then from the websocket-server module: `mvn clean package`