package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Hire;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Phone;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_NRIC = "T01B";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_HIRE = "2023-13-01";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_LEAVE_START = "1st Jan 2025";
    private static final String INVALID_LEAVE_END = "2nd Jan 2025";
    private static final String INVALID_LEAVE_REASON = "";
    private static final int INVALID_ATTENDANCE_WORKDAYCOUNT = -1;
    private static final int INVALID_ATTENDANCE_ABSENTDAYCOUNT = -1;

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_NRIC = BENSON.getNric().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_HIRE = BENSON.getHire().toString();

    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().getTags().stream()
        .map(JsonAdaptedTag::new)
        .collect(Collectors.toList());
    private static final List<JsonAdaptedLeave> VALID_LEAVES = BENSON.getLeaves().stream()
            .map(JsonAdaptedLeave::new)
            .collect(Collectors.toList());

    private static final JsonAdaptedAttendance VALID_ATTENDANCE = new JsonAdaptedAttendance(BENSON.getAttendance());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(INVALID_NAME, VALID_NRIC, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_HIRE, VALID_TAGS, VALID_LEAVES, VALID_ATTENDANCE);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(null, VALID_NRIC, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_HIRE, VALID_TAGS, VALID_LEAVES, VALID_ATTENDANCE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidNric_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, INVALID_NRIC, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_HIRE, VALID_TAGS, VALID_LEAVES, VALID_ATTENDANCE);
        String expectedMessage = Nric.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullNric_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, null, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_HIRE, VALID_TAGS, VALID_LEAVES, VALID_ATTENDANCE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Nric.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_NRIC, INVALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_HIRE, VALID_TAGS, VALID_LEAVES, VALID_ATTENDANCE);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_NRIC, null, VALID_EMAIL,
                VALID_ADDRESS, VALID_HIRE, VALID_TAGS, VALID_LEAVES, VALID_ATTENDANCE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_NRIC, VALID_PHONE, INVALID_EMAIL,
                VALID_ADDRESS, VALID_HIRE, VALID_TAGS, VALID_LEAVES, VALID_ATTENDANCE);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_NRIC, VALID_PHONE, null,
                VALID_ADDRESS, VALID_HIRE, VALID_TAGS, VALID_LEAVES, VALID_ATTENDANCE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_NRIC, VALID_PHONE, VALID_EMAIL,
                        INVALID_ADDRESS, VALID_HIRE, VALID_TAGS, VALID_LEAVES, VALID_ATTENDANCE);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_NRIC, VALID_PHONE, VALID_EMAIL,
                null, VALID_HIRE, VALID_TAGS, VALID_LEAVES, VALID_ATTENDANCE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidHire_throwsIllegalValueException() {
        JsonAdaptedPerson person =
            new JsonAdaptedPerson(VALID_NAME, VALID_NRIC, VALID_PHONE, VALID_EMAIL,
                    VALID_ADDRESS, INVALID_HIRE, VALID_TAGS, VALID_LEAVES, VALID_ATTENDANCE);
        String expectedMessage = Hire.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullHire_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_NRIC, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, null, VALID_TAGS, VALID_LEAVES, VALID_ATTENDANCE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Hire.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_NRIC, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_HIRE, invalidTags, VALID_LEAVES, VALID_ATTENDANCE);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidLeaves_throwsIllegalValueException() {
        List<JsonAdaptedLeave> invalidLeaves = new ArrayList<>(VALID_LEAVES);
        invalidLeaves.add(new JsonAdaptedLeave(INVALID_LEAVE_START, INVALID_LEAVE_END, INVALID_LEAVE_REASON));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_NRIC, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_HIRE, VALID_TAGS, invalidLeaves, VALID_ATTENDANCE);
        assertThrows(IllegalValueException.class, person::toModelType);
    }
    @Test
    public void toModelType_invalidAttendance_throwsIllegalValueException() {
        JsonAdaptedAttendance invalidAttendance =
                new JsonAdaptedAttendance(INVALID_ATTENDANCE_WORKDAYCOUNT, INVALID_ATTENDANCE_ABSENTDAYCOUNT);
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_NRIC, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_HIRE, VALID_TAGS, VALID_LEAVES, invalidAttendance);
        assertThrows(IllegalValueException.class, person::toModelType);
    }
}
