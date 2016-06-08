/**
 * 
 */
package liquibase.ext.filter;

import java.util.List;
import java.util.Set;

import liquibase.changelog.ChangeSet;
import liquibase.changelog.DatabaseChangeLog;
import liquibase.database.Database;
import liquibase.exception.PreconditionErrorException;
import liquibase.exception.PreconditionFailedException;
import liquibase.exception.ValidationErrors;
import liquibase.exception.Warnings;
import liquibase.parser.core.ParsedNode;
import liquibase.parser.core.ParsedNodeException;
import liquibase.precondition.Precondition;
import liquibase.resource.ResourceAccessor;

/**
 * @author oded.nissan
 *
 */
public class MyPreCond implements Precondition {

	/* (non-Javadoc)
	 * @see liquibase.serializer.LiquibaseSerializable#getSerializableFieldNamespace(java.lang.String)
	 */
	public String getSerializableFieldNamespace(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see liquibase.serializer.LiquibaseSerializable#getSerializableFieldType(java.lang.String)
	 */
	public SerializationType getSerializableFieldType(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see liquibase.serializer.LiquibaseSerializable#getSerializableFieldValue(java.lang.String)
	 */
	public Object getSerializableFieldValue(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see liquibase.serializer.LiquibaseSerializable#getSerializableFields()
	 */
	public Set<String> getSerializableFields() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see liquibase.serializer.LiquibaseSerializable#getSerializedObjectName()
	 */
	public String getSerializedObjectName() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see liquibase.serializer.LiquibaseSerializable#getSerializedObjectNamespace()
	 */
	public String getSerializedObjectNamespace() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see liquibase.serializer.LiquibaseSerializable#serialize()
	 */
	public ParsedNode serialize() throws ParsedNodeException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see liquibase.precondition.Precondition#check(liquibase.database.Database, liquibase.changelog.DatabaseChangeLog, liquibase.changelog.ChangeSet)
	 */
	public void check(Database db, DatabaseChangeLog dblog, ChangeSet changeSet)
			throws PreconditionFailedException, PreconditionErrorException {
		// TODO Auto-generated method stub
		List<ChangeSet> chSets = dblog.getChangeSets();
		// sort by ids and get the last change set.

	}

	/* (non-Javadoc)
	 * @see liquibase.precondition.Precondition#getName()
	 */
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see liquibase.precondition.Precondition#load(liquibase.parser.core.ParsedNode, liquibase.resource.ResourceAccessor)
	 */
	public void load(ParsedNode arg0, ResourceAccessor arg1)
			throws ParsedNodeException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see liquibase.precondition.Precondition#validate(liquibase.database.Database)
	 */
	public ValidationErrors validate(Database arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see liquibase.precondition.Precondition#warn(liquibase.database.Database)
	 */
	public Warnings warn(Database arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
