/**
 * 
 */
package liquibase.ext.filter;

import liquibase.changelog.ChangeSet;
import liquibase.changelog.filter.ChangeSetFilter;
import liquibase.changelog.filter.ChangeSetFilterResult;

/**
 * @author oded.nissan
 *
 */
public class MyFilter implements ChangeSetFilter {
	

	/* (non-Javadoc)
	 * @see liquibase.changelog.filter.ChangeSetFilter#accepts(liquibase.changelog.ChangeSet)
	 */
	
	public MyFilter() {
		super();
		// TODO Auto-generated constructor stub
		System.out.println("MyFilter()");
	}

	public ChangeSetFilterResult accepts(ChangeSet change) {
		// TODO Auto-generated method stub
		String id = change.getId();
		System.out.println("id = " + id);
		
		ChangeSetFilterResult result = new ChangeSetFilterResult(true, null, MyFilter.class);
		return (result);
	}

	
}
