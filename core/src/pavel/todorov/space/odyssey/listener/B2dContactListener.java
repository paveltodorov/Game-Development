package pavel.todorov.space.odyssey.listener;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import pavel.todorov.space.odyssey.model.Player;

public class B2dContactListener implements ContactListener {
    private String playerClass = "pavel.todorov.space.odyssey.model.Player";
    private String wallClass = "pavel.todorov.space.odyssey.model.CosmicDebrie";
    private String giftClass = "pavel.todorov.space.odyssey.model.Gift";
    private Player player;

    public B2dContactListener(Player player) {
        this.player = player;
    }

    @Override
    public void beginContact(Contact contact) {
        String classA = contact.getFixtureA().getBody().getUserData().getClass().getName();
        String classB = contact.getFixtureB().getBody().getUserData().getClass().getName();


        //System.out.println(classA);
        //System.out.println(classB);

        if (classA.equals(playerClass) && classB.equals(wallClass)) {
            int playerLifes = player.getLifes();
            //System.out.println(playerLifes);
            if (playerLifes == 1) {
                player.die();
            } else {
                player.removeOneLife();
            }
        } else if (classA.equals(wallClass) && classB.equals(playerClass)) {
            int playerLifes = player.getLifes();
            if (playerLifes == 0) {
                player.die();
            } else {
                player.removeOneLife();
            }
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        String classA = contact.getFixtureA().getBody().getUserData().getClass().getName();
        String classB = contact.getFixtureB().getBody().getUserData().getClass().getName();

        if (classA.equals(playerClass) && classB.equals(giftClass)) {
            // player.moveBy(1f,1f);
            contact.setEnabled(false);
            player.incrementGiftsTaken();
           /* int playerLifes = player.getLifes();
            //System.out.println(playerLifes);
            if(playerLifes == 1) {
                player.die();
            }
            else {
                player.removeOneLife();
            }*/
        } else if (classA.equals(wallClass) && classB.equals(giftClass)) {
            // player.moveBy(1f,1f);
            //contact.setEnabled(false);
            player.incrementGiftsTaken();
            contact.setEnabled(false);
           /* int playerLifes = player.getLifes();
            if(playerLifes == 0) {
                player.die();
            }
            else {
                player.removeOneLife();
            }*/
        }

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
