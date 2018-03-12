package 基本操作;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * Created by junmeng.xu on 2016/11/15.
 */
public class ReflectNameChain {
    private String nameChain;
    private Method method;
    private Object obj;
    private Type fType;

    public ReflectNameChain() {
    }

    public String getNameChain() {
        return this.nameChain;
    }

    public void setNameChain(String nameChain) {
        this.nameChain = nameChain;
    }

    public Method getMethod() {
        return this.method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object getObj() {
        return this.obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public Type getfType() {
        return this.fType;
    }

    public void setfType(Type fType) {
        this.fType = fType;
    }

    public int hashCode() {
        boolean prime = true;
        byte result = 1;
        int result1 = 31 * result + (this.fType == null?0:this.fType.hashCode());
        result1 = 31 * result1 + (this.method == null?0:this.method.hashCode());
        result1 = 31 * result1 + (this.nameChain == null?0:this.nameChain.hashCode());
        result1 = 31 * result1 + (this.obj == null?0:this.obj.hashCode());
        return result1;
    }

    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        } else if(obj == null) {
            return false;
        } else if(this.getClass() != obj.getClass()) {
            return false;
        } else {
            ReflectNameChain other = (ReflectNameChain)obj;
            if(this.fType == null) {
                if(other.fType != null) {
                    return false;
                }
            } else if(!this.fType.equals(other.fType)) {
                return false;
            }

            if(this.method == null) {
                if(other.method != null) {
                    return false;
                }
            } else if(!this.method.equals(other.method)) {
                return false;
            }

            if(this.nameChain == null) {
                if(other.nameChain != null) {
                    return false;
                }
            } else if(!this.nameChain.equals(other.nameChain)) {
                return false;
            }

            if(this.obj == null) {
                if(other.obj != null) {
                    return false;
                }
            } else if(!this.obj.equals(other.obj)) {
                return false;
            }

            return true;
        }
    }

    public String toString() {
        return "ReflectNameChain [nameChain=" + this.nameChain + ", method=" + this.method + ", obj=" + this.obj + ", fType=" + this.fType + "]";
    }
}
