package com.arsframework.annotation.processor;

import java.lang.annotation.Annotation;

import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.type.MirroredTypeException;

import com.arsframework.annotation.Nonempty;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.tree.JCTree;

/**
 * 参数非空校验注解处理器
 *
 * @author yongqiang.wu
 */
@SupportedAnnotationTypes("com.arsframework.annotation.Nonempty")
public class NonemptyValidateProcessor extends AbstractValidateProcessor {
    /**
     * 获取异常类名称
     *
     * @param nonempty 校验注解实例
     * @return 类名称
     */
    protected String getException(Nonempty nonempty) {
        try {
            return nonempty.exception().getCanonicalName();
        } catch (MirroredTypeException e) {
            return e.getTypeMirror().toString();
        }
    }

    @Override
    protected JCTree.JCIf buildValidateCondition(Symbol.VarSymbol param, Class<? extends Annotation> annotation) {
        Nonempty nonempty = (Nonempty)Validates.lookupAnnotation(param, annotation);
        JCTree.JCExpression condition = Validates.buildEmptyExpression(maker, names, param, nonempty.blank());
        return Validates.buildValidateException(maker, names, param, condition, this.getException(nonempty),
            nonempty.message(), param.name.toString());
    }
}
