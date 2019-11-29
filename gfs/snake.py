from gamegrid import *

class Snake(Actor):
    def __init__(self, color, length):
        Actor.__init__(self, "lightout_1.gif")
        self.hide()
        self.color = color
        self.position = [0,0]
        self.history = []
        self.length = length
        self.orlength = length
        self.d = 2
        self.changedDir = True
              
    def act(self):
        self.mov()
        self.shows()
        
    def mov(self):
        self.history.append(self.position[:])
        if self.d == 0:
            self.position[1] -= 1
        elif self.d == 1:
            self.position[0] += 1
        elif self.d == 2:
            self.position[1] += 1
        elif self.d == 3:
            self.position[0] -= 1
        else:
            pass
        
        #reset
        if self.position in self.history or self.wall():
            grid.doReset()
            for i in self.history:
                l = Location(i[0], i[1])
                grid.getBg().fillCell(l, Color.blue)
            self.history = []
            self.position = [0,0]
            self.d = 2
            self.length = self.orlength
            food.reposition()
            doRun()
    
        self.setX(self.position[0])
        self.setY(self.position[1])
        self.move()
        self.changedDir = False

    def wall(self):
        return self.position[0] < 0 or self.position[0] >= 20 or self.position[1] < 0 or self.position[1] >= 20

    def shows(self):
        cut = None
        if len(self.history) > self.length:
            cut = self.history[0]
            self.history = self.history[1:]
        for i in self.history:
           l = Location(i[0], i[1])
           grid.getBg().fillCell(l, self.color)
        if cut != None:
           grid.getBg().fillCell(Location(cut[0], cut[1]), Color.blue)
        grid.getBg().fillCell(Location(self.position[0], self.position[1]), self.color)
        
    def chDir(self, d):
        if self.changedDir:
            return
        if not ((self.d == 0 and d == 2) or (self.d == 1 and d == 3) or (self.d == 2 and d == 0) or (self.d == 3 and d == 1)):
            self.d = d
            self.changedDir = True
            
    def collide(self):
        self.length += 1        
    
class Food(Actor):
    def __init__(self, color):
        Actor.__init__(self, "lightout_1.gif")
        self.hide()
        self.position = grid.getRandomEmptyLocation()
        self.color = color
        
    def collide(self, snake):
        if snake.position[0] == self.position.getX() and snake.position[1] == self.position.getY():
            self.reposition()
            snake.collide()
            
    def reposition(self):
        grid.getBg().fillCell(self.position, Color.blue)
        self.position = grid.getRandomEmptyLocation()
        while grid.getBg().getColor(self.position) != Color.blue:
            self.position = grid.getRandomEmptyLocation()        
    
    def act(self):
        self.shows()
        self.collide(snake)
        
    def shows(self):
        grid.getBg().fillCell(self.position, self.color)
        
snake = Snake(Color.green, 3)
def onKeyPressed(e):
    key = e.getKeyCode()
    if key == 87 or key == 38:
        snake.chDir(0)
    elif key == 68 or key == 39:
        snake.chDir(1)
    elif key == 83 or key == 40:
        snake.chDir(2)
    elif key == 65 or key == 37:
        snake.chDir(3)
        
    
grid = makeGameGrid(20, 20, 20, False, keyPressed=onKeyPressed)
setBgColor(Color.blue)
addActor(snake, Location(0,0))
food = Food(Color.red)
addActor(food, food.position)
show()
doRun()
