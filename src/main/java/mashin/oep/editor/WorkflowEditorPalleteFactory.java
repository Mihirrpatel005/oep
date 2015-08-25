package mashin.oep.editor;

import mashin.oep.Activator;
import mashin.oep.model.node.action.basic.FSActionNode;
import mashin.oep.model.node.action.basic.JavaActionNode;
import mashin.oep.model.node.action.basic.MapReduceActionNode;
import mashin.oep.model.node.action.basic.PigActionNode;
import mashin.oep.model.node.action.basic.SubWorkflowActionNode;
import mashin.oep.model.node.action.extended.CustomActionNode;
import mashin.oep.model.node.action.extended.DistcpActionNode;
import mashin.oep.model.node.action.extended.EmailActionNode;
import mashin.oep.model.node.action.extended.Hive2ActionNode;
import mashin.oep.model.node.action.extended.HiveActionNode;
import mashin.oep.model.node.action.extended.SSHActionNode;
import mashin.oep.model.node.action.extended.ShellActionNode;
import mashin.oep.model.node.action.extended.SparkActionNode;
import mashin.oep.model.node.action.extended.SqoopActionNode;
import mashin.oep.model.node.control.DecisionNode;
import mashin.oep.model.node.control.ForkNode;
import mashin.oep.model.node.control.JoinNode;
import mashin.oep.model.node.control.KillNode;

import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PaletteToolbar;
import org.eclipse.gef.palette.PanningSelectionToolEntry;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.jface.resource.ImageDescriptor;

public class WorkflowEditorPalleteFactory {

  private static PaletteContainer createToolsGroup(PaletteRoot palette) {
    PaletteToolbar toolbar = new PaletteToolbar("Tools");

    // Add a selection tool to the group
    ToolEntry tool = new PanningSelectionToolEntry();
    toolbar.add(tool);
    palette.setDefaultEntry(tool);

    // Add a marquee tool to the group
    toolbar.add(new MarqueeToolEntry());

    // Add (solid-line) connection tool
    tool = new ConnectionCreationToolEntry("Connection",
        "Create a connection", null,
        ImageDescriptor.createFromFile(Activator.class, "icons/connection16.gif"),
        ImageDescriptor.createFromFile(Activator.class, "icons/connection24.gif"));
    toolbar.add(tool);

    return toolbar;
  }
  
  private static PaletteContainer createControlNodesGroup(WorkflowEditor workflowEditor) {
    PaletteDrawer controlNodesDrawer = new PaletteDrawer("Control Nodes");

    CombinedTemplateCreationEntry component = null;
    
    //CombinedTemplateCreationEntry component = new CombinedTemplateCreationEntry(
    //    "End", "Create an End node",
    //    EndNode.class, new NodeCreationFactory<EndNode>(workflowEditor, EndNode.class),
    //    ImageDescriptor.createFromFile(Activator.class, "icons/rectangle16.gif"),
    //    ImageDescriptor.createFromFile(Activator.class, "icons/rectangle24.gif"));
    //controlNodesDrawer.add(component);

    component = new CombinedTemplateCreationEntry(
        "Kill", "Create a Kill node",
        KillNode.class, new NodeCreationFactory<KillNode>(workflowEditor, KillNode.class),
        ImageDescriptor.createFromFile(Activator.class, "icons/rectangle16.gif"),
        ImageDescriptor.createFromFile(Activator.class, "icons/rectangle24.gif"));
    controlNodesDrawer.add(component);
    
    component = new CombinedTemplateCreationEntry(
        "Decision", "Create a Decision node",
        DecisionNode.class, new NodeCreationFactory<DecisionNode>(workflowEditor, DecisionNode.class),
        ImageDescriptor.createFromFile(Activator.class, "icons/rectangle16.gif"),
        ImageDescriptor.createFromFile(Activator.class, "icons/rectangle24.gif"));
    controlNodesDrawer.add(component);
    
    component = new CombinedTemplateCreationEntry(
        "Fork", "Create a Fork node",
        ForkNode.class, new NodeCreationFactory<ForkNode>(workflowEditor, ForkNode.class),
        ImageDescriptor.createFromFile(Activator.class, "icons/rectangle16.gif"),
        ImageDescriptor.createFromFile(Activator.class, "icons/rectangle24.gif"));
    controlNodesDrawer.add(component);
    
    component = new CombinedTemplateCreationEntry(
        "Join", "Create a Join node",
        JoinNode.class, new NodeCreationFactory<JoinNode>(workflowEditor, JoinNode.class),
        ImageDescriptor.createFromFile(Activator.class, "icons/rectangle16.gif"),
        ImageDescriptor.createFromFile(Activator.class, "icons/rectangle24.gif"));
    controlNodesDrawer.add(component);

    return controlNodesDrawer;
  }

  private static PaletteContainer createBasicActionNodesGroup(WorkflowEditor workflowEditor) {
    PaletteDrawer actionNodesDrawer = new PaletteDrawer("Action Nodes");

    CombinedTemplateCreationEntry component = new CombinedTemplateCreationEntry(
        "MapReduce", "Create a MapReduce node",
        MapReduceActionNode.class, new NodeCreationFactory<MapReduceActionNode>(workflowEditor, MapReduceActionNode.class),
        ImageDescriptor.createFromFile(Activator.class, "icons/rectangle16.gif"),
        ImageDescriptor.createFromFile(Activator.class, "icons/rectangle24.gif"));
    actionNodesDrawer.add(component);

    component = new CombinedTemplateCreationEntry(
        "Pig", "Create a Pig node",
        PigActionNode.class, new NodeCreationFactory<PigActionNode>(workflowEditor, PigActionNode.class),
        ImageDescriptor.createFromFile(Activator.class, "icons/rectangle16.gif"),
        ImageDescriptor.createFromFile(Activator.class, "icons/rectangle24.gif"));
    actionNodesDrawer.add(component);
    
    component = new CombinedTemplateCreationEntry(
        "Sub Workflow", "Create a Sub Workflow node",
        SubWorkflowActionNode.class, new NodeCreationFactory<SubWorkflowActionNode>(workflowEditor, SubWorkflowActionNode.class),
        ImageDescriptor.createFromFile(Activator.class, "icons/rectangle16.gif"),
        ImageDescriptor.createFromFile(Activator.class, "icons/rectangle24.gif"));
    actionNodesDrawer.add(component);
    
    component = new CombinedTemplateCreationEntry(
        "FS", "Create an FS node",
        FSActionNode.class, new NodeCreationFactory<FSActionNode>(workflowEditor, FSActionNode.class),
        ImageDescriptor.createFromFile(Activator.class, "icons/rectangle16.gif"),
        ImageDescriptor.createFromFile(Activator.class, "icons/rectangle24.gif"));
    actionNodesDrawer.add(component);
    
    component = new CombinedTemplateCreationEntry(
        "Java", "Create a Java node",
        JavaActionNode.class, new NodeCreationFactory<JavaActionNode>(workflowEditor, JavaActionNode.class),
        ImageDescriptor.createFromFile(Activator.class, "icons/rectangle16.gif"),
        ImageDescriptor.createFromFile(Activator.class, "icons/rectangle24.gif"));
    actionNodesDrawer.add(component);

    return actionNodesDrawer;
  }
  
  private static PaletteContainer createExtendedActionNodesGroup(WorkflowEditor workflowEditor) {
    PaletteDrawer extendedActionNodesDrawer = new PaletteDrawer("Extended Action Nodes");

    CombinedTemplateCreationEntry component = new CombinedTemplateCreationEntry(
        "SSH", "Create an SSH node",
        SSHActionNode.class, new NodeCreationFactory<SSHActionNode>(workflowEditor, SSHActionNode.class),
        ImageDescriptor.createFromFile(Activator.class, "icons/rectangle16.gif"),
        ImageDescriptor.createFromFile(Activator.class, "icons/rectangle24.gif"));
    extendedActionNodesDrawer.add(component);

    component = new CombinedTemplateCreationEntry(
        "Sqoop", "Create a Sqoop node",
        SqoopActionNode.class, new NodeCreationFactory<SqoopActionNode>(workflowEditor, SqoopActionNode.class),
        ImageDescriptor.createFromFile(Activator.class, "icons/rectangle16.gif"),
        ImageDescriptor.createFromFile(Activator.class, "icons/rectangle24.gif"));
    extendedActionNodesDrawer.add(component);
    
    component = new CombinedTemplateCreationEntry(
        "Spark", "Create a Spark node",
        SparkActionNode.class, new NodeCreationFactory<SparkActionNode>(workflowEditor, SparkActionNode.class),
        ImageDescriptor.createFromFile(Activator.class, "icons/rectangle16.gif"),
        ImageDescriptor.createFromFile(Activator.class, "icons/rectangle24.gif"));
    extendedActionNodesDrawer.add(component);
    
    component = new CombinedTemplateCreationEntry(
        "Shell", "Create a Shell node",
        ShellActionNode.class, new NodeCreationFactory<ShellActionNode>(workflowEditor, ShellActionNode.class),
        ImageDescriptor.createFromFile(Activator.class, "icons/rectangle16.gif"),
        ImageDescriptor.createFromFile(Activator.class, "icons/rectangle24.gif"));
    extendedActionNodesDrawer.add(component);
    
    component = new CombinedTemplateCreationEntry(
        "Distcp", "Create a Distcp node",
        DistcpActionNode.class, new NodeCreationFactory<DistcpActionNode>(workflowEditor, DistcpActionNode.class),
        ImageDescriptor.createFromFile(Activator.class, "icons/rectangle16.gif"),
        ImageDescriptor.createFromFile(Activator.class, "icons/rectangle24.gif"));
    extendedActionNodesDrawer.add(component);

    component = new CombinedTemplateCreationEntry(
        "Hive", "Create a Hive node",
        HiveActionNode.class, new NodeCreationFactory<HiveActionNode>(workflowEditor, HiveActionNode.class),
        ImageDescriptor.createFromFile(Activator.class, "icons/rectangle16.gif"),
        ImageDescriptor.createFromFile(Activator.class, "icons/rectangle24.gif"));
    extendedActionNodesDrawer.add(component);
    
    component = new CombinedTemplateCreationEntry(
        "Hive2", "Create a Hive2 node",
        Hive2ActionNode.class, new NodeCreationFactory<Hive2ActionNode>(workflowEditor, Hive2ActionNode.class),
        ImageDescriptor.createFromFile(Activator.class, "icons/rectangle16.gif"),
        ImageDescriptor.createFromFile(Activator.class, "icons/rectangle24.gif"));
    extendedActionNodesDrawer.add(component);
    
    component = new CombinedTemplateCreationEntry(
        "Email", "Create an Email node",
        EmailActionNode.class, new NodeCreationFactory<EmailActionNode>(workflowEditor, EmailActionNode.class),
        ImageDescriptor.createFromFile(Activator.class, "icons/rectangle16.gif"),
        ImageDescriptor.createFromFile(Activator.class, "icons/rectangle24.gif"));
    extendedActionNodesDrawer.add(component);
    
    component = new CombinedTemplateCreationEntry(
        "Custom Action", "Create a Custom Action node",
        CustomActionNode.class, new NodeCreationFactory<CustomActionNode>(workflowEditor, CustomActionNode.class),
        ImageDescriptor.createFromFile(Activator.class, "icons/rectangle16.gif"),
        ImageDescriptor.createFromFile(Activator.class, "icons/rectangle24.gif"));
    extendedActionNodesDrawer.add(component);
    
    return extendedActionNodesDrawer;
  }
  
  /**
   * Creates the PaletteRoot and adds all Palette elements.
   * 
   * @return the root
   */
  public static PaletteRoot createPalette(WorkflowEditor workflowEditor) {
    PaletteRoot workflowPalette = new PaletteRoot();
    workflowPalette.add(createToolsGroup(workflowPalette));
    workflowPalette.add(createControlNodesGroup(workflowEditor));
    workflowPalette.add(createBasicActionNodesGroup(workflowEditor));
    workflowPalette.add(createExtendedActionNodesGroup(workflowEditor));
    return workflowPalette;
  }
  
}