package anaofind.lib.anadatair.printer;

import anaofind.lib.anadatair.sql.*;
import anaofind.lib.anadatair.visitor.VisitorSQL;

import java.util.*;

/**
 * pretty printer sql
 * @author anaofind
 */
public class PrettyPrinterSQL extends PrettyPrinter implements VisitorSQL{

	/**
	 * construct
	 */
	public PrettyPrinterSQL() {
		super(' ');
	}
	
	@Override
	public void visitSelectSQL(SQLSelect select) {
		Set<String> tables = select.getTables();
		List<String> columns = select.getColumns();
		List<SQLValue> conditions = select.getConditions();
		
		this.addText(0, "SELECT");
		if (columns.size() > 0) {
			int columnCount = columns.size();
			for (String column : columns) {
				this.addText(1, column);
				columnCount --;
				if (columnCount > 0) {
					this.addText(0, ",");
				}
			}
		} else {
			this.addText(1, "*");
		}
		this.jumpLine(1);
		this.addText(0, "FROM");
		
		int tableCount = tables.size();
		for (String table : tables) {
			this.addText(1, table);
			tableCount--;
			if (tableCount > 0) {
				this.addText(0, ",");
			}
		}
		this.jumpLine(1);
		
		int countCondition = conditions.size();
		for (SQLValue sqlValue : conditions) {
			if (countCondition < conditions.size()) {
				this.addText(0, "AND");
				this.addText(1, "");
			}
			sqlValue.accept(this);
			countCondition --;
			if (countCondition > 0) {
				this.jumpLine(1);
			}
		}
	}

	@Override
	public void visitWhereSQL(SQLWhere where) {
		this.addText(0, "WHERE " + where.getColumn() + " " + where.getOp() + " ");
		where.getComparable().accept(this);
	}

	@Override
	public void visitOrderBySQL(SQLOrderBy orderBy) {
		this.addText(0, "ORDER BY " + orderBy.getColumn() + " \'" + orderBy.getOrder() + "\'");
	}

	@Override
	public void visitNumberSQL(SQLNumber number) {
		if (number.getType().equals("integer")) {
			this.addText(0, "\'" + (int)number.getValue() + "\'");
		} else {
			this.addText(0, "\'" + number.getValue() + "\'");	
		}
	}

	@Override
	public void visitStringSQL(SQLString string) {
		this.addText(0, "\'" + string.getValue() + "\'");
	}
	
	
	public static void main(String[] args) {
		SQLWhere cond1 = new SQLWhere("age", ">", new SQLNumber(10));
		SQLOrderBy cond2 = new SQLOrderBy("nom");
		SQLSelect select = new SQLSelect("personne");
		select.addColumns("personne", "age", "nom", "prenom");
		select.addCondition(cond1);
		select.addCondition(cond2);
		PrettyPrinterSQL pp = new PrettyPrinterSQL();
		select.accept(pp);
		pp.print();
	}

}
