FROM oven/bun:alpine AS base

FROM base AS builder

RUN apk add --no-cache gcompat
WORKDIR /app

COPY package.json bun.lockb tsconfig.json  ./
COPY src ./src/

RUN bun install
RUN bun run build

FROM base AS runner
WORKDIR /app


COPY --from=builder /app/node_modules /app/node_modules
COPY --from=builder /app/dist /app/dist
COPY --from=builder /app/package.json /app/package.json

RUN bun install --production
RUN bunx playwright install
EXPOSE 3000:3000

ENTRYPOINT  ["node", "/app/dist/index.js"]