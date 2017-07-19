package budget.ui;

import org.junit.Assert;
import org.junit.Test;

import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by 1 on 18.07.2017.
 */
public class DatePickerComponentTest {
    @Test
    public void testGetDateFromPicker() throws Exception {
        LocalDate date = LocalDate.of(2017, 1, 1);
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        date = today.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        DatePickerComponent component = new DatePickerComponent();
        component.createComponent(new Dimension());

        Assert.assertEquals("Wrong date conversion from picker", date, component.getDateFromPicker());
    }

}