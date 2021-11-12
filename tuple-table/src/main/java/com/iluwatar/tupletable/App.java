package com.iluwatar.tupletable;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>The Tuple table pattern stores an object in the database in a highly flexible format that can
 * be manipulated at the database level by normal human beings and can be easily extended
 * without having to convert existing data.  Much of the data used in an enterprise application
 * can be condensed into sets of fields. We can use this approach to persist objects to the
 * database by assigning each object instance a primary key and then storing labeled values for
 * each key (a tuple).</p>
 *
 * <p>The primary advantage of this approach is extreme flexibility. By storing object fields as
 * name and value pairs, we can modify te application to include new fields without changing
 * the underlying database structure.</p>
 *
 * <p>The primary disadvantage of the tuple table pattern is in its integrity enforcement.
 * Relational databased are very good at enforcing rules at the column level, but aren't so good
 * at enforcing rules as the data level.</p>
 *
 * <p>Here the APP is demo class or housing entry point void main.
 * In this method, an instance of MemberTupleDAO is created which is implementation based on tuple
 * pattern, having methods such as find member, save member.
 * This App class demonstrates the usage of the implementation by retrieving/querying, saving and
 * displaying to logger.</p>
 *
 * <p>This solution uses java reflection with DAO object to retrieve an object from a tuple table
 * or write it back to the table, we can retrieve field names directly form the object we are
 * persisting.</p>
 *
 * <p>The OBJECT_DATA table doesn't know about the particular object types; its counting on the
 * application to ensure that no two objects share a primary key.</p>
 */

@Slf4j
public class App {
  /**
   * The entry void main function to build MemberTupleDAO and for the usage of findMember()
   * different set methods within the tuple available as setMemberNumber, setAddress1, etc.
   * This demonstrates the capability of the tuple table java pattern implementation for
   * saving and retrieving data.
   *
   * @throws SQLException from MemberTupleDao
   */
  public static void main(String[] args) throws SQLException, ClassNotFoundException,
          InvocationTargetException, IllegalAccessException {
    if (LOGGER.isInfoEnabled()) {
      //Instantiate the MemberTupleDAO
      MemberTupleDao mtd = new MemberTupleDao();
      mtd.createTableIfNotExists();
      //Retrieve the information saved in DB already - first time it should show null
      MemberDto member = mtd.findMember(1);
      //Access and display the information based on the search criteria
      LOGGER.info(member.getFirstName() + " " + member.getLastName());
      LOGGER.info(String.valueOf(member.getFreePasses()));
      LOGGER.info(member.getAddress1());
      LOGGER.info(member.getCity());
      //Set a different member information using the tuple pattern implementation
      member.setMemberNumber(4);
      member.setFirstName("Atif");
      member.setLastName("Ahmed");
      member.setAddress1("USA");
      member.setAddress2("USA");
      member.setCity("Columbus");
      member.setState("Ohio");
      member.setZip("12345");
      mtd.saveMember(member);
      //Find and display above saved information
      member = mtd.findMember(4);
      LOGGER.info(member.getFirstName() + " " + member.getLastName());
      LOGGER.info(member.getAddress1());
      LOGGER.info(member.getAddress2());
      LOGGER.info(member.getCity());
      LOGGER.info(member.getState());
      LOGGER.info(member.getZip());
    }
  }
}