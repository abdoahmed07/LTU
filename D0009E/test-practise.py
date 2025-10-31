class circle:
    def __init__(self, x, y, r):
        self.x = x
        self.y = y
        self.r = r
        
    def new(self, r):
        return (circle(self.x, self.y, r))
    
    def sync(self, another_circle):
        self.x = another_circle.x
        self.y = another_circle.y



def smurf(lst, s, k=0):
    if k == len(lst):            # base case: no more indices
        return []
    ch = s[lst[k]]               # pick character at that index
    return [ch] + smurf(lst, s, k+1)  # combine with recursive rest



def checkSum(lst):
    i=0
    while i<len(lst):
        j = i+1
        while j<len(lst):
            if lst[i]+lst[j]==10:
                return True
            j+=1
        i+=1
    return False


def smurf2(lst, s):
    if len(lst) == 0:      #if the list is empty then return an empty list
        return []
    return s[lst[0]] + smurf2(lst[1:], s) # it starts with the first index in the list then sends the rest of the list to the same function again. and then it takes the first index again which is the second one in the real list. and keeps doing so until the list is empty and it sends back nothing. 



def pairSumFilt (lst1, lst2, s): 
    lst = [0] * len(lst1)
    k = 0;
    while k < len(lst1):
        if lst1[k] + lst2[k] == s:
            lst [k] = lst1[k]
        else:
            lst [k] = lst2[k]
        k += 1
    return lst





def writeToFile(sLst, filename):
    try:
        f = open(filename,"x")
        for e in sLst:
            if e == "":
                f.close()
                raise ValueError ("Empty string")
            f.write(e + "\n")
        f.close()
    except FileExistsError:
        raise FileExistsError ("File already exists")

def inputList():  # do not write or modify this
    sLst = []
    while True:
        s = input()
        if s == "exit":
            break
        sLst.append(s)
    return sLst

def writeUI():
    try:
        sLst = inputList()
        writeToFile(sLst, filename = input("Enter filename: "))
    except FileExistsError:
        print("File already exists")
    except ValueError:
        print("Empty string")
        
    
    
    
class Pstring:
    def __init__(self, s):
        self.s = s
        
    def prefix(self, n):
        return Pstring(self.s[:n])
    
    def gets(self):
        return self.s
    
    def add(self,other):
        self.s += other.s
        
        
def unique(lst):
    if len(lst) == 0:
        return []
    
    if lst[0] in lst[1:]:
        return unique(lst[1:])
    return lst[0] + unique(lst[1:])



class DynString:
    def __init__(self, s):
        self.s = s
        
    def copy(self):
        return DynString(self.s)
    
    def aff(self, other):
        self.s += other.s
        
    def __str(self):
        return self.s
    
    
    
    
def deleteEven(lst):
    if 0 == len(lst):
        return []
    
    if lst[0] % 2 == 0:
        lst.pop(0)
    else:
        return lst[0] + deleteEven(lst[1:])
    return deleteEven(lst)


def selectShortest(lstA, lstB):
    if len(lstA)>len(lstB):
        return lstB
    else:
        return lstA
def updateFirst(lst, e):
    lst[0] = e
    
    
lst1 = [4,6,8]
lst2 = selectShortest(lst1,[3,9])
updateFirst(lst2, 0)
lst3 = selectShortest(lst2,lst1)
print('lst1=',lst1)
print('lst2=',lst2)
print('lst3=',lst3)
if id(lst1)==id(lst2):
    print("lst1=lst2")
if id(lst1)==id(lst3):
    print("lst1=lst3")
if id(lst2)==id(lst3):
    print("lst2=lst3")  




