/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package deepspace;

/**
 *
 * @author yabir
 */
public enum WeaponType {
        LASER(2.0f),
        MISSILE(3.0f),
        PLASMA(4.0f);
        
        private float power;
        
        WeaponType(float power){
            this.power = power;
        }
        
        public float getPower(){
            return power;
        }       
        
}