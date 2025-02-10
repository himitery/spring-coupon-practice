import { check } from "k6";
import http from "k6/http";
import { Trend } from "k6/metrics";

const TOTAL_USERS = 200;

export const options = {
  stages: [
    { duration: "10s", target: TOTAL_USERS * 0.1 },
    { duration: "20s", target: TOTAL_USERS * 0.8 },
    { duration: "5s", target: 0 },
  ],
};

const userIds = Array.from({ length: TOTAL_USERS }, (_, i) => `user-${i + 1}`);

const trends = {
  responseTime: new Trend("response_time"),
};

export const setup = () => {
  const response = http.post(
    "http://localhost:8080/api/v1/admin/coupon/new",
    JSON.stringify({
      name: `test-${Date.now()}`,
      amount: 100,
    }),
    {
      headers: { "Content-Type": "application/json" },
    }
  );
  if (response.status !== 200) {
    throw new Error(`Failed to fetch UUID: ${response.status}`);
  }

  return { id: response.json().id };
};

export default (data) => {
  const userId = userIds.sort(() => Math.random() - 0.5)[(__VU - 1) % TOTAL_USERS];

  const response = http.post(
    `http://localhost:8080/api/v1/coupon/${data.id}/issue`,
    JSON.stringify({}),
    {
      headers: {
        "Content-Type": "application/json",
        "X-USER-ID": userId,
      },
    }
  );

  check(response, {
    "is status 200": (r) => r.status === 200,
    "is status 404": (r) => r.status === 404,
  });

  trends.responseTime.add(response.timings.duration);

  if (response.status === 200 || response.status === 404) {
    console.log(`User: ${userId} | Status: ${response.status} | Response: ${response.body}`);
  }
};
