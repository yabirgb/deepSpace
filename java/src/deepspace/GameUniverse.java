/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package deepspace;
import java.util.ArrayList;

public class GameUniverse {
    
    private static final int WIN = 10;
    
    private int currentStationIndex;
    private SpaceStation currentStation;
    private EnemyStarShip currentEnemy; 
    private ArrayList<SpaceStation> spaceStations;
    
    private int turns;
    private Dice dice = new Dice();
    private GameStateController gameState = new GameStateController();
    private CardDealer dealer;
    
    public GameUniverse(){
        turns = 0;
    }
    
    public CombatResult combat(){
        GameState state = gameState.getState();   
        if (state == GameState.BEFORECOMBAT || state == GameState.INIT)
            return combat(currentStation, currentEnemy);
        else
            return CombatResult.NOCOMBAT;
    }
    
    CombatResult combat(SpaceStation station, EnemyStarShip enemy){
        
        GameState state = gameState.getState();
        if (state == GameState.BEFORECOMBAT || state == GameState.INIT){
            GameCharacter ch = dice.firstShot();
            
            boolean enemyWins;
            CombatResult combatResult = CombatResult.STATIONESCAPES;
            
            if (ch == GameCharacter.ENEMYSTARSHIP){
                
                float fire = station.fire();
                ShotResult result = station.receiveShot(fire);
                
                if (result == ShotResult.RESIST){
                    fire = station.fire();
                    result = enemy.receiveShot(fire);
                    enemyWins = result == ShotResult.RESIST;
                }
                else{
                    enemyWins = true;
                }
            }else{
                float fire = station.fire();
                ShotResult result = enemy.receiveShot(fire);
                enemyWins = result == ShotResult.RESIST;
            }
            
            if (enemyWins){
                float s = station.getSpeed();
                boolean moves = dice.spaceStationMoves(s);
                
                if (moves){
                    station.move();
                    combatResult = CombatResult.STATIONESCAPES;
                }else{
                    Damage damage = enemy.getDamage();
                    station.setPendingDamage(damage);
                    combatResult = CombatResult.STATIONWINS;
                }
            }
            
            gameState.next(turns, spaceStations.size());
            return combatResult;
        }    
        else
            return CombatResult.NOCOMBAT;
            
    }
    
    public void discardHangar(){
        if (getState() == GameState.INIT || getState() == GameState.AFTERCOMBAT){
            spaceStations.get(currentStationIndex).discardHangar();
        }
    }
    
    public void discardWeapon(int i){
        if (getState() == GameState.INIT || getState() == GameState.AFTERCOMBAT){
            spaceStations.get(currentStationIndex).discardWeapon(i);
        }
    }
    
    public void discardWeaponInHangar(int i){
        if (getState() == GameState.INIT || getState() == GameState.AFTERCOMBAT){
            spaceStations.get(currentStationIndex).discardWeaponInHangar(i);
        }
    }
    
    public void discardShieldBooster(int i){
        if (getState() == GameState.INIT || getState() == GameState.AFTERCOMBAT){
            spaceStations.get(currentStationIndex).discardShieldBooster(i);
        }   
    }
    
    public void discardShieldBoosterInHangar(int i){
        if (getState() == GameState.INIT || getState() == GameState.AFTERCOMBAT){
            spaceStations.get(currentStationIndex).discardShieldBoosterInHangar(i);
        }
    }
    
    public GameState getState(){
        return gameState.getState();
    }
    
    public GameUniverseToUI getUIversion(){
        return new GameUniverseToUI(currentStation, currentEnemy);
    }
    
    public boolean haveAWinner(){
        return spaceStations.get(currentStationIndex).getNMedals() == WIN;
    }
    
    public void init(ArrayList<String> names){
        if (getState() == GameState.CANNOTPLAY){
            spaceStations = new ArrayList<>();
            dealer = CardDealer.getInstance();
            
            for(String name: names){
                SuppliesPackage supplies = dealer.nextSuppliesPackage();
                SpaceStation station = new SpaceStation(name,supplies);
                int nh = dice.initWithNHangars();
                int nw = dice.initWithNWeapons();
                int ns = dice.initWithNShields();
                
                Loot l = new Loot(0,nw,ns,nh,0);
                
                station.setLoot(l);
                spaceStations.add(station);
            }
            
            currentStationIndex = dice.whoStarts(names.size());
            currentStation = spaceStations.get(currentStationIndex);
            currentEnemy = dealer.nextEnemy();
            gameState.next(turns, spaceStations.size());
        }
    }
    
    public void mountWeapon(int i){
        if (getState() == GameState.INIT || getState() == GameState.AFTERCOMBAT){
            spaceStations.get(currentStationIndex).mountWeapon(i);
        }
    }
    
    public void mountShieldBooster(int i){
        if (getState() == GameState.INIT || getState() == GameState.AFTERCOMBAT){
            spaceStations.get(currentStationIndex).mountShieldBooster(i);
        }
    }
    
    public boolean nextTurn(){
        
        if (getState() == GameState.AFTERCOMBAT){
            boolean stationState = currentStation.validState();
            
            if (stationState){
                currentStationIndex = (currentStationIndex + 1) % spaceStations.size();
                turns += 1;
                
                currentStation = spaceStations.get(currentStationIndex);
                currentStation.cleanUpMountedItems();
                dealer = CardDealer.getInstance();
                currentEnemy = dealer.nextEnemy();
                gameState.next(turns, spaceStations.size());
                return true;
            }
        }
        
        return false;
    }
}
