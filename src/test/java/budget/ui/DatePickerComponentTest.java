package budget.ui;

import org.junit.Assert;
import org.junit.Test;

import java.awt.*;
import java.time.LocalDate;

/**
 * Created by 1 on 18.07.2017.
 */
public class DatePickerComponentTest {
    @Test
    public void getDateFromPickerTest() throws Exception {
        LocalDate date = LocalDate.of(2017, 1, 1);
        DatePickerComponent component = new DatePickerComponent();
        component.createComponent(new Dimension());
        component.getDateField().setSelectedIndex(0);
        component.getMonthField().setSelectedIndex(0);
        component.getYearField().setSelectedIndex(0);

        Assert.assertEquals("Should be 2017-01-01", date, component.getDateFromPicker());
    }

}