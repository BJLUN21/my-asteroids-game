import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

module Core {
	requires Common;
	requires java.desktop;
	requires spring.context;
	requires spring.beans;
	requires javafx.graphics;
	requires java.net.http;

	opens dk.sdu.mmmi.cbse.main to javafx.graphics, spring.core;

	uses IGamePluginService;
	uses IEntityProcessingService;
	uses IPostEntityProcessingService;

	exports dk.sdu.mmmi.cbse.main;
}