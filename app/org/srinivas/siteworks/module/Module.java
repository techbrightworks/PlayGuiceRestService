package org.srinivas.siteworks.module;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import org.srinivas.siteworks.calculate.Calculate;
import org.srinivas.siteworks.calculate.OptimalCalculator;
import org.srinivas.siteworks.calculate.SupplyCalculator;
import org.srinivas.siteworks.changeservice.ChangeService;
import org.srinivas.siteworks.changeservice.ChangeServiceImpl;
import org.srinivas.siteworks.data.PropertiesReadWriter;




/**
 * This class is a Guice module that tells Guice how to bind several
 * different types. This Guice module is created when the Play
 * application starts.
 *
 * Play will automatically use any class called `Module` that is in
 * the root package. You can create modules in other locations by
 * adding `play.modules.enabled` settings to the `application.conf`
 * configuration file.
 */
public class Module extends AbstractModule {

    @Override
    public void configure() {
        bind(ChangeService.class).to(ChangeServiceImpl.class);
        bind(Calculate.class).annotatedWith(Names.named("Supply")).to(SupplyCalculator.class);
        bind(Calculate.class).annotatedWith(Names.named("Optimal")).to(OptimalCalculator.class);
        bind(PropertiesReadWriter.class).asEagerSingleton();
    }

}
