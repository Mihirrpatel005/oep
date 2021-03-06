/**
 * Copyright (c) 2015 Mashin (http://mashin.io). All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.mashin.oep.model.node.action.basic;

import io.mashin.oep.hpdl.XMLReadUtils;
import io.mashin.oep.hpdl.XMLWriteUtils;
import io.mashin.oep.model.SchemaVersion;
import io.mashin.oep.model.Workflow;
import io.mashin.oep.model.property.PreparePropertyElement;
import io.mashin.oep.model.property.PropertyElementCollection;
import io.mashin.oep.model.property.PropertyPropertyElement;
import io.mashin.oep.model.property.TextPropertyElement;
import io.mashin.oep.model.property.filter.SchemaVersionRangeFilter;

import org.dom4j.Element;
import org.dom4j.Node;

public class PigActionNode extends BasicActionNode {

  public static final String PROP_JOBTRACKER = "prop.node.pig.job-tracker";
  public static final String PROP_NAMENODE = "prop.node.pig.name-ndoe";
  public static final String PROP_PREPARE = "prop.node.pig.prepare";
  public static final String PROP_JOBXML = "prop.node.pig.job-xml";
  public static final String PROP_CONFIGURATION = "prop.node.pig.configuration";
  public static final String PROP_SCRIPT = "prop.node.pig.script";
  public static final String PROP_PARAM = "prop.node.pig.param";
  public static final String PROP_ARGUMENT = "prop.node.pig.argument";
  public static final String PROP_FILE = "prop.node.pig.file";
  public static final String PROP_ARCHIVE = "prop.node.pig.archive";
  
  protected TextPropertyElement jobTracker;//job-tracker
  protected TextPropertyElement nameNode;//name-node
  
  //prepare
  protected PreparePropertyElement prepare;

  protected PropertyElementCollection jobXML;//job-xml 0-unbounded
  protected PropertyElementCollection configuration;//configuration
  protected TextPropertyElement script;//script
  protected PropertyElementCollection param;//param 0-unbounded
  protected PropertyElementCollection argument;//argument 0-unbounded
  protected PropertyElementCollection file;//file 0-unbounded
  protected PropertyElementCollection archive;//archive o-unbounded
  
  public PigActionNode(Workflow workflow) {
    this(workflow, null);
  }

  public PigActionNode(Workflow workflow, org.dom4j.Node hpdlNode) {
    super(workflow, hpdlNode);
    
    jobTracker = new TextPropertyElement(PROP_JOBTRACKER, "Jobtracker");
    addPropertyElement(jobTracker);
    
    nameNode = new TextPropertyElement(PROP_NAMENODE, "Namenode");
    addPropertyElement(nameNode);

    //prepare
    prepare = new PreparePropertyElement(PROP_PREPARE, "Prepare");
    addPropertyElement(prepare);

    jobXML = new PropertyElementCollection("Job XML", new TextPropertyElement(PROP_JOBXML, "Job XML"));
    addPropertyElement(jobXML);
    
    configuration = new PropertyElementCollection("Configuration",
                      new PropertyPropertyElement(PROP_CONFIGURATION, "Configuration"));
    addPropertyElement(configuration);
    
    script = new TextPropertyElement(PROP_SCRIPT, "Script");
    addPropertyElement(script);
    
    param = new PropertyElementCollection("Param", new TextPropertyElement(PROP_PARAM, "Param"));
    addPropertyElement(param);
    
    argument = new PropertyElementCollection("Argument", new TextPropertyElement(PROP_ARGUMENT, "Argument"),
        new SchemaVersionRangeFilter(SchemaVersion.V_0_2, SchemaVersion.V_ANY, workflow));
    addPropertyElement(argument);
    
    file = new PropertyElementCollection("File", new TextPropertyElement(PROP_FILE, "File"));
    addPropertyElement(file);
    
    archive = new PropertyElementCollection("Archive", new TextPropertyElement(PROP_ARCHIVE, "Archive"));
    addPropertyElement(archive);
  }
  
  @Override
  public void initDefaults() {
    super.initDefaults();
    setName(workflow.nextId("pig"));
  }
  
  @Override
  public void write(Element paretNode) {
    super.write(paretNode);
    
    Element element = (Element) hpdlModel.get();
    Element pig = element.addElement("pig");
    
    XMLWriteUtils.writeTextPropertyAsElement(jobTracker, pig, "job-tracker");
    XMLWriteUtils.writeTextPropertyAsElement(nameNode, pig, "name-node");
    XMLWriteUtils.writePrepareProperty(prepare, pig, "prepare");
    XMLWriteUtils.writeTextCollectionAsElements(jobXML, pig, "job-xml");
    XMLWriteUtils.writePropertiesCollection(configuration, pig, "configuration", "property");
    XMLWriteUtils.writeTextPropertyAsElement(script, pig, "script");
    XMLWriteUtils.writeTextCollectionAsElements(param, pig, "param");
    XMLWriteUtils.writeTextCollectionAsElements(argument, pig, "argument");
    XMLWriteUtils.writeTextCollectionAsElements(file, pig, "file");
    XMLWriteUtils.writeTextCollectionAsElements(archive, pig, "archive");
    
    writeConnections(element);
  }
  
  @Override
  public void read(Node hpdlNode) {
    super.read(hpdlNode);
    
    XMLReadUtils.initTextPropertyFrom(jobTracker, hpdlNode, "./pig/job-tracker");
    XMLReadUtils.initTextPropertyFrom(nameNode, hpdlNode, "./pig/name-node");
    XMLReadUtils.initPreparePropertyFrom(prepare, hpdlNode, "./pig/prepare");
    XMLReadUtils.initTextCollectionFrom(jobXML, hpdlNode, "./pig/job-xml");
    XMLReadUtils.initPropertiesCollectionFrom(configuration, hpdlNode, "./pig/configuration", "./property");
    XMLReadUtils.initTextPropertyFrom(script, hpdlNode, "./pig/script");
    XMLReadUtils.initTextCollectionFrom(param, hpdlNode, "./pig/param");
    XMLReadUtils.initTextCollectionFrom(argument, hpdlNode, "./pig/argument");
    XMLReadUtils.initTextCollectionFrom(file, hpdlNode, "./pig/file");
    XMLReadUtils.initTextCollectionFrom(archive, hpdlNode, "./pig/archive");
  }
  
  @Override
  public String getNodeType() {
    return TYPE_PIG;
  }
  
}
