package it.infocert.eigor.api;

import it.infocert.eigor.model.core.rules.Br002AnInvoiceShallHaveAnInvoiceNumberRule;
import it.infocert.eigor.model.core.rules.Rule;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

public class ReflectionBasedRepositoryTest {

    @Test public void shouldFindRules() {

        // given
        ReflectionBasedRepository sut = new ReflectionBasedRepository();
        Class<? extends Rule> aRuleThatShouldBeFound = Br002AnInvoiceShallHaveAnInvoiceNumberRule.class;

        // when
        List<Rule> allRules = sut.rules();

        // then
        List<Rule> rules = allRules.stream().filter(r -> r.getClass().equals(aRuleThatShouldBeFound)).collect(toList());
        assertThat( rules, Matchers.hasSize(1) );
        assertThat( rules.get(0), instanceOf(aRuleThatShouldBeFound));

    }

    @Test public void shouldFindToCen() {

        // given
        ReflectionBasedRepository sut = new ReflectionBasedRepository();
        Class<? extends ToCENConversion> aConversionThatShouldBeFound = FakeToCENConversion.class;

        // when
        ToCENConversion conversion = sut.findConversionToCen("fake");

        // then
        assertThat( conversion, notNullValue() );
        assertThat( conversion, instanceOf(aConversionThatShouldBeFound));

    }

    @Test public void shouldFindFromCen() {

        // given
        ReflectionBasedRepository sut = new ReflectionBasedRepository();
        Class<? extends FromCENConverter> aConversionThatShouldBeFound = FakeFromCenConverter.class;

        // when
        FromCENConverter conversion = sut.findConversionFromCen("fake");

        // then
        assertThat( conversion, notNullValue() );
        assertThat( conversion, instanceOf(aConversionThatShouldBeFound));

    }

}