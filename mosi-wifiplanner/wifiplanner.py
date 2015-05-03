#!/usr/bin/python3
# -*- coding: utf-8 -*-
"""
Created on Tue Apr 21 05:34:25 2015

@author: rizki
"""
import numpy as np
from random import randint
from matplotlib.pylab import matshow,zeros
import matplotlib.pyplot as plt

#VARS
LENGTH=80
WIDTH=30
N_AP=11
AP_RADIUS=12
SIZE=LENGTH*WIDTH

#GA Parameters
POP_SIZE=15
MUTATION_RATE=0.7
CHROMOSOME_LENGTH=SIZE

def createIndividual():
    pos=[]
    for i in range(N_AP):
        row = np.random.randint(0,WIDTH)
        col = np.random.randint(0,LENGTH)
        pos.append([row,col])
    pos.sort()
    return pos
    
def estimateCoverage(individu):
    board = np.zeros((WIDTH,LENGTH),dtype=np.int)    
    for x in individu:
        a,b=x[0],x[1]
        r = AP_RADIUS
        y,x = np.ogrid[-a:WIDTH-a, -b:LENGTH-b]
        mask = x*x + y*y <= r*r
        board[mask] = 1
    return board
        
def generatePopulation(pop_size):
    pop=[]
    for i in range(pop_size):
        pop.append(createIndividual())
    return pop

def fitnessCalc(individu):
    count=0
    board = estimateCoverage(individu)
    for row in board:
        for col in row:
            if col == 1:
                count+=1
    return round(count/SIZE,4)
    
def grade(populasi):
    fitness=[]
    for individu in populasi:
        fitness.append(fitnessCalc(individu))
    return [populasi[fitness.index(max(fitness))],max(fitness)]
    
def evolve(populasi,mutate=MUTATION_RATE):
    newGen = populasi[POP_SIZE//2:]    
    newGen_len = len(newGen)
    children_len = len(populasi) - newGen_len
    children = []
    while len(children) < children_len:
        induk01 = randint(0,POP_SIZE-1)
        induk02 = randint(0,POP_SIZE-1)
        if(induk01!=induk02):
            induk01 = populasi[induk01]
            induk02 = populasi[induk02]
            half = len(induk01)//2
            child = induk01[:half] + induk02[half:]
            children.append(child)
    newGen.extend(children)
    for individu in newGen:
        if mutate > np.random.rand(1):
            position = np.random.randint(0,len(individu)-1)
            individu[position]=[np.random.randint(WIDTH),np.random.randint(LENGTH)]
    return newGen
    
####################################################################
log_generasi=[]
log_fitness=[]

logFile_fitness = open('fitness_set','w')
logFile_best = open('best_individu','w')

for i in range(11):
    if(i==0):
        populasi = generatePopulation(POP_SIZE)
        log_generasi.append(populasi)
    else:
        populasi = evolve(populasi)    
        log_generasi.append(populasi)

for pop in log_generasi:
    log_fitness.append(grade(pop))

i=0
bestIndividu = log_fitness[0]
for individu in log_fitness: 
    print('Generasi ke-{:d}\tFitness: {:.2f}\n'.format(i,individu[1]),file=logFile_fitness)
    i+=1
    if(bestIndividu[1] < individu[1]):
        bestIndividu = individu
print(bestIndividu,file=logFile_best)

boardCoverage = estimateCoverage(bestIndividu[0])
boardLoc = zeros((30,80))
for row in boardCoverage:
    for col in row:
        if col==1:
            boardCoverage[row][col]=np.random.random()
for x in bestIndividu[0]:
    boardLoc[x[0],x[1]]= np.random.random()
matshow(boardCoverage)
matshow(boardLoc)
