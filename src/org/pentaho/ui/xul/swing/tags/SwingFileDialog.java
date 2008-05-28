package org.pentaho.ui.xul.swing.tags;

import java.awt.Component;
import java.io.File;

import javax.swing.JFileChooser;

import org.pentaho.ui.xul.XulComponent;
import org.pentaho.ui.xul.XulDomContainer;
import org.pentaho.ui.xul.components.XulFileDialog;
import org.pentaho.ui.xul.dom.Element;
import org.pentaho.ui.xul.swing.SwingElement;

public class SwingFileDialog extends SwingElement implements XulFileDialog{
  
  JFileChooser fc;
  private SEL_TYPE selectionType = SEL_TYPE.SINGLE;
  private VIEW_TYPE viewType = VIEW_TYPE.FILES_DIRECTORIES;
  private File selectedFile;
  private File[] selectedFiles;
  
  public SwingFileDialog(Element self, XulComponent parent, XulDomContainer domContainer, String tagName) {
    super("filedialog");
    domContainer.getDocumentRoot().getRootElement().addChild(this);
  }

  public File getFile() {
    return fc.getSelectedFile();  
  }

  public File[] getFiles() {
    return fc.getSelectedFiles();  
  }

  public SEL_TYPE getSelectionMode() {
    return selectionType;
  }

  public VIEW_TYPE getViewType() {
    return viewType;
  }

  public void setSelectionMode(SEL_TYPE type) {
    this.selectionType = type;   
  }

  public void setViewType(VIEW_TYPE type) {
    this.viewType = type;   
  }

  public RETURN_CODE showOpenDialog() {
    fc = new JFileChooser();
    return showOpen();
  }
  
  public RETURN_CODE showOpenDialog(File f) {
    fc = new JFileChooser(f);
    return showOpen();
  }

  public RETURN_CODE showSaveDialog() {

    fc = new JFileChooser();
    return showSave();
  }

  public RETURN_CODE showSaveDialog(File f) {
    fc = new JFileChooser(f);
    return showSave();
  }
  
  private RETURN_CODE showOpen(){
    int retVal = fc.showOpenDialog((Component)this.getDocument().getRootElement().getManagedObject());
    switch(retVal){
      case JFileChooser.APPROVE_OPTION:
        if(this.selectionType == SEL_TYPE.SINGLE){
          selectedFile = fc.getSelectedFile();
        } else {
          selectedFiles = fc.getSelectedFiles();
        }
        return RETURN_CODE.OK;
      case JFileChooser.CANCEL_OPTION:
      default:
        return RETURN_CODE.CANCEL;
    }
  }
  
  private RETURN_CODE showSave(){
    int retVal = fc.showSaveDialog((Component)this.getDocument().getRootElement().getManagedObject());
    switch(retVal){
      case JFileChooser.APPROVE_OPTION:
        selectedFile = fc.getSelectedFile();
        return RETURN_CODE.OK;
      case JFileChooser.CANCEL_OPTION:
      default:
        return RETURN_CODE.CANCEL;
    }
  }
  
}

  