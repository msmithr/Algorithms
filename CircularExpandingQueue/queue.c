#include <stdio.h>
#include <stdlib.h>

// unbounded circular array implemetation for queue

#define INITIAL_CAP 5

typedef struct queue {
    int front;
    int back;
    int cap;
    int size;
    int *data;
} queue_t;

void queue_init();
void enqueue(int elem);
int dequeue();
void queue_expand();
void print_queue();

queue_t queue;

void queue_init() {
    queue.front = 0;
    queue.back = 0;
    queue.cap = INITIAL_CAP;
    queue.size = 0;
    queue.data = malloc(sizeof(int) * INITIAL_CAP);
}

void enqueue(int elem) {
    if (queue.size == queue.cap) {
        queue_expand();
    }

    queue.data[queue.back] = elem;
    queue.back = (queue.back + 1) % queue.cap;

    queue.size++;
}

int dequeue() {
    queue.front = (queue.front + 1) % queue.cap;
    queue.size--;
    return queue.data[(queue.front - 1) % queue.cap];
}

void queue_expand() {
    int *new_array = malloc(sizeof(int) * queue.size * 2);
    int i = 0;
    for (int j = 0; j < queue.size; j++) {
        new_array[i] = queue.data[(j + queue.front) % queue.cap];
        i++;
    }

    queue.front = 0;
    queue.back = queue.size;

    queue.cap *= 2;
    free(queue.data);
    queue.data = new_array;


    return;
}

void print_queue() {
    printf("Data: ");
    for (int i = 0; i < queue.cap; i++) {
        printf("%d ", queue.data[i]);
    }
    printf("\n");
    printf("Queue: ");
    
    for (int i = 0; i < queue.size; i++) {
        printf("%d ", queue.data[(i + queue.front) % queue.cap]);
    }

    printf("\n");
//    printf("Front: %d\n", queue.front);
//    printf("Back: %d\n", queue.back);
}

int main(int argc, char *argv[]) {
    queue_init();
    enqueue(5);
    enqueue(3);
    enqueue(2);
    dequeue();
    enqueue(5);
    dequeue();
    dequeue();
    enqueue(3);
    enqueue(2);
    dequeue();
    dequeue();
    dequeue();
    enqueue(5);
    enqueue(3);
    enqueue(2);
    enqueue(3);
    enqueue(3);
    enqueue(3);
    dequeue();
    dequeue();
    dequeue();
    print_queue();
}
