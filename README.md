# Frogger Project
![Frogger, Pixel Image](/data/frogger2.png)

## What Does this application do?

This application is a take on the popular 1980s arcade game Frogger.
The game gives the user control of a frog whose objective is to cross the street/lake while avoiding cars and other 
obstacles. The user's score will be increased every time they successfully make it to the other side of the map.
If the user hits an obstacle the game will restart.

**Some potential features of the game** :
- Keeps track of high score
- Various different obstacles and enemies
> *Cars, trucks, crocodiles, etc* 
- Increases difficulty as score increases
- Different types of frogs to play as





## Intended User
**People who might enjoy this game**:
- Video game and arcade game enthusiast 
- Anyone with 1980s nostalgia 
>*Whether they were alive during the decade or not*
- Boomers, Zoomers, Millenials, and Gen x 
- Anyone with free time

This game is basically intended for anyone with free time who wants to play a computer version of the 1980s arcade game 
Frogger that includes some new features. Anyone who was a fan of this game from  the past.



## Personal Interest 
I am personally interested in recreating this game because I love video games and I have already created several simple
web games using Javascript and a text based game in Python. 

I would love to use my
new Java knowledge to recreate a classic game with both UI and graphical interface. I choose Frogger because 
it is a game that occurs on a single screen/map and it requires quite simple graphics. I also have good memories of
playing an online version of the game on my computer in the early 2000s.
 
I love the Retro feel of these early games and
think there will be creative ways to showcase the required features for each phase in the game. 
 
 ## User Stories 
- As a user, I want to be able to be able to move my character
- As a user, I want my score to increase as I progress
- As a user, I want to add bonus items to my inventory
- As a video game  enthusiast, I want to be challenged by encountering enemies in the game
- As a user, I want collisions with enemies to restart my progress
- As a user, I want to be able to optionally save my highest score and/or progress when I quit or get game over
- As a user, I want to be able to load a game file saved with my progress and highest 
score so I can try to beat it next game or continue where I left off

## Instructions For Grader
 -  You can generate the first required event by moving your player with the arrow keys
 and moving over a green lilypad or fly image. This will add the item to your inventory, you can
 check that it has been added by pressing the inventory menu and viewing how the inventory changes. 
 This event Adds an X to a Y.
 - You can generate the second required event by clicking the inventory menu and hovering over an item name
 and clicking the "Use" option. You will see the score go up and that the item has now been removed from your inventory.
 This event uses an X and removes an X from Y. 
 - You can trigger my audio component by picking up an item. When you move over an item 
 you will hear the GemChest1-1.wav sound as it is added to your inventory.
 - You can save the state of my application by pressing the "Save Your Game" button, use the file chooser window 
 to locate the data folder of this project and write a name of your choosing then press save. 
 Note make sure you are out of reach of cars before saving. 
 - You can reload the state of my application by pressing the "Load a Game" button. 
 Use the file chooser window to locate the data folder of this project, choose the file you just saved and press open.
 Your game will be loaded in the same state you saved it in. 
 You can confirm this by noting your position, inventory, and score have loaded. 
 - Note- Running into cars will get you game over, 
 reaching the end of the map vertically or using an item increases your score. 
 You can exit the game at any time by pressing the "X" key. 
 The json files that are already in the data folder are for testing Persistence and will not load properly, 
 as the game has been updated since their creation, please DO NOT try to load these and load files you have saved instead. 
 
 ## Phase 4: Task 2
 Type Hierarchy  
 
 Sprite is the abstract super class of the entire hierarchy, it has two methods, a move method and an abstract draw method.
 The draw method is overridden in the Items class so that depending on the name of the item a different image is drawn.
 Frog is another subtype of Sprite, it does not override move but it uses move in it's directional movement methods 
 and overrides draw by simply drawing a frog image wherever the frog is located. 
 There is a Enemy abstract subclass that overrides the move method by moving one unit to the right. 
 The car class is a subtype of Enemy it does not override Enemy's move function but it overrides draw 
 by simply drawing a car image. The truck class is another subtype
 of Enemy, it overrides draw and move. For move, it moves faster and a different direction. It moves to the left a unit + 2 
 instead of right. And for draw it draws a truck image.
  The Alligator class is the last subtype of Enemy, it overrides move by
 setting a unique field to visible or not depending on x-position and then calling super to move one unit right. 
 Depending on if its visible field is set to true or not it will either draw water or an alligator image. 
 If the Alligator is not visible the player will not lose if they collide into it.  
 
 ##  Phase 4: Task 3.
 
 ### Problems 
 1. In the Frogger UI class there is functionality for a load button 
 and in the Stats panel there is functionality for a save button. This violates the single responsibility principle ,
 as the stats panel should just handle display and interactions of the in game stats and the Frogger class 
 should handle creating the Jframe and updating it's components. Therefore I should create a new Class for the 
 creation and file choosers associated with the save/load buttons to increase cohesion and maybe let the 
 Frogger class deal with all the action handling to further increase it's cohesion. 
 
 2. There is too much coupling between the Frogger class, the  Panel classes and the FroggerGame class. As the Frogger
 class has a FroggerGame field but so do all it's panels. 
 I should try to decrease this by getting rid of these all unnecessary 
 associations and rooting them through the FroggerGame class.
 
 ### Fixes
 
 1. I fixed the first problem by introducing a new class called FileChooserPanel,
which is a new JPanel that contains code for both the creation and customization of the save/load buttons as well as
save/load methods that utilize a file chooser. I then deleted the redundant button creation methods from the Frogger class and
Stats Panel, copied and pasted the action handling code relating to loading/saving from Frogger and Stats Panel
to Frogger and then simplified it using FileChooserPanels new methods. 
I then made a new FileChooserPanel field in the Frogger class called fcp and added/instantiated
 it in the Frogger Constructor. Now the save/load buttons and code relating to them are created in one class and
 all the action handling is dealt with in Frogger making it more cohesive as well. 
 
 2. I fixed the second problem by deleting the game field from StatsPanel and FileChooserPanel. I instead added a 
 FroggerGame parameter to all of the relevant methods, I also added an action listener parent parameter to methods that
 used to rely on the class as a ActionListener. 
 I created a new method called updateMenu() in StatsPanel and moved the action handling of the inventory to the Frogger
 class, as it is the only action listener now. I was then able to remove any associations between StatsPanel, 
 FileChooserPanel and FroggerGame which really decreased my coupling and improved my UML diagram. Now that Frogger class
 deals with all action listening this fix also increased cohesion too as Stats Panel 
 simply creates and updates the stats/menu panel, FileChooser styles and creates save/load buttons 
 and handles saving and loading. And Frogger updates the frame and handles action events. Now the only panel with an 
 association to FroggerGame is the GamePanel and this association is necessary as it's paint component is inherited 
 and cannot be changed, so the only way to draw a game is to have it as a field.  
 