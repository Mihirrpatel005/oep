package mashin.oep.model.connection;

import mashin.oep.model.node.Node;
import mashin.oep.model.property.TextPropertyElement;
import mashin.oep.model.terminal.Terminal;

public class WorkflowCaseConnection extends WorkflowConnection {

  public static final String PROP_CONN_CONDITION = "prop.conn.condition";
  
  protected TextPropertyElement condition;
  
  public WorkflowCaseConnection(Node source, Node target,
      Terminal sourceTerminal, Terminal targetTerminal) {
    super(source, target, sourceTerminal, targetTerminal);
    condition = new TextPropertyElement(PROP_CONN_CONDITION, "Condition");
    addPropertyElement(condition);
  }
  
  public void setCondition(String condition) {
    setPropertyValue(PROP_CONN_CONDITION, condition);
  }
  
  public String getCondition() {
    return condition.getStringValue();
  }

  @Override
  public boolean equals(Object obj) {
    if (!super.equals(obj) || !(obj instanceof WorkflowCaseConnection)) {
      return false;
    }
    
    return this.getCondition().equals(
        ((WorkflowCaseConnection) obj).getCondition());
  }
  
}
