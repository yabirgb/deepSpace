/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package deepspace;

/**
 *
 * @author yabir
 */
public interface SpaceFighter {
    public float fire();
    public float protection();
    public ShotResult receiveShot(float shot);
}
