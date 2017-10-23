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
}

int dequeue() {
    queue.front = (queue.front + 1) % queue.cap;
    return queue.data[(queue.front - 1) % queue.cap];
}

void queue_expand() {
    // TODO
    return;
}

void print_queue() {
    printf("Data: ");
    for (int i = 0; i < queue.cap; i++) {
        printf("%d ", queue.data[i]);
    }
    printf("\n");
    printf("Queue: ");
    for (int i = queue.front; i != queue.back; i = (i + 1) % queue.cap) {
        printf("%d ", queue.data[i]);
    }
    printf("\n");
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
    print_queue();
}
