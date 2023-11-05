package com.srijan.commentgen;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.command.WriteCommandAction;

public class CodeCommentAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getProject();
        Editor editor = e.getData(PlatformDataKeys.EDITOR);

        if (project == null || editor == null) {
            return;// Handle cases where project or editor is not available
        }

        // Get the document and selection model
        Document document = editor.getDocument();
        SelectionModel selectionModel = editor.getSelectionModel();

        // Get the selected/Highlighted text
        String selectedText = selectionModel.getSelectedText();

        if (selectedText != null && !selectedText.isEmpty()) {

            String comment = "/*Generated Comment: This is a sample comment for the highlighted code.*/";

            // Insert the comment at the start of the selected text
            int selectionStart = selectionModel.getSelectionStart();
            WriteCommandAction.runWriteCommandAction(project, () -> {
                document.insertString(selectionStart, comment + "\n");
            });
        } else {
            Messages.showMessageDialog(project, "Please select the code to generate a comment for.", "Info", Messages.getInformationIcon());
        }
    }
}

