package ru.nsu.fit.tests;

import java.util.List;

import org.jbehave.core.Embeddable;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import ru.nsu.fit.tests.shared.AllureReporter;
import ru.nsu.fit.tests.steps.HousewifeSteps;
import ru.nsu.fit.tests.steps.MainSteps;
import ru.nsu.fit.tests.steps.SchoolboySteps;
import ru.nsu.fit.tests.steps.ScientistSteps;
import ru.yandex.qatools.allure.annotations.Stories;

import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;
import static org.jbehave.core.reporters.Format.CONSOLE;
import static org.jbehave.core.reporters.Format.HTML;

public class MainStories extends JUnitStories {
    @org.testng.annotations.Test
    public void run() throws Throwable {
        super.run();
    }

    @Override
    public Configuration configuration() {
        Class<? extends Embeddable> embeddableClass = this.getClass();
        return new MostUsefulConfiguration()
                .useStoryLoader(new LoadFromClasspath(embeddableClass))
                .useStoryReporterBuilder(new StoryReporterBuilder()
                        .withCodeLocation(CodeLocations.codeLocationFromClass(embeddableClass))
                        .withDefaultFormats()
                        .withFormats(CONSOLE, HTML)
                        .withReporters(new AllureReporter())
                        .withFailureTrace(true)
                        .withFailureTraceCompression(true));
    }
//7
    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(), new MainSteps(), new HousewifeSteps(), new SchoolboySteps(),
                new ScientistSteps());
    }

    @Override
    protected List<String> storyPaths() {
        return new StoryFinder().findPaths(codeLocationFromClass(this.getClass()), "**/*.story", "");
    }
}

